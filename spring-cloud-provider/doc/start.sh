# ----- 信息 -----
# @filename start.sh
# @version 1.0
# @author qye.zheng
# @description 启动脚本

# 解决远程ssh执行脚本失败问题
source /etc/profile

# 进入脚本所在目录
cd `dirname $0`

# 变量定义
# 服务名称
declare -r SVC_NAME='spring-cloud-provider'
# jar包名称
declare -r SVC_JAR_NAME="$SVC_NAME-0.0.1.jar"
# 服务路径
declare -r SVC_DEP_HOME="/usr/local/spring-cloud"
# 日志路径
declare -r SVC_LOG_HOME="/data/logs/$SVC_NAME"
# 进程ID文件
PID_FILE="/opt/pid/${SVC_NAME}-svc.pid"
# 环境
declare -r ENV="dev"
# 服务端口号
declare -r SVC_PORT=7070
# 程序(Jar包)参数
declare -r PROGRAM_OPTS=""
declare -r RUN_JAVA='java'
# 检查次数
CHECK_COUNT=3

# 创建目录
mkdir -p ${SVC_DEP_HOME}
mkdir -p ${SVC_LOG_HOME}

# 进入应用所在目录（虽然都是绝对路径，但有些应用需要进入应用目录才能启动成功）
cd ${SVC_DEP_HOME}

# JVM 参数配置，-Xms最小内存，-Xmx最大内存
JAVA_OPTS="-Xms128m -Xmx256m \
-XX:MetaspaceSize=128m \
-XX:MaxMetaspaceSize=128m \
-Xss768k \
-Xmn256m \
-XX:+AggressiveOpts \
-XX:+UseBiasedLocking \
-XX:+CMSParallelRemarkEnabled \
-XX:+UseConcMarkSweepGC \
-XX:ParallelGCThreads=2 \
-XX:SurvivorRatio=4 \
-XX:TargetSurvivorRatio=80 \
-verbose:gc \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps \
-XX:+PrintHeapAtGC \
-Xloggc:$SVC_LOG_HOME/gc.log \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=$SVC_LOG_HOME/dump.logs"

# 进程状态标识变量，1为存在，0为不存在
PID_FLAG=0

# 使用说明，用来提示输入参数
usage() {
    echo "usage: sh svc.sh [start|stop|restart|status]"
    exit 1
}

# 检查进程状态
check_pid(){
  if [[ -f "$PID_FILE" ]]; then
    PID=`cat "$PID_FILE" | awk '{print $1}'`
    PID=`ps -aef | grep "$PID" | awk '{print $2}' | grep "$PID"`
    if [[ -z "$PID" ]]; then
      PID_FLAG=0
    else
      PID_FLAG=1
    fi
  else
    PID=''
    PID_FLAG=0
  fi
}

# 启动
start(){
  check_pid
  if [[ ${PID_FLAG} -ne 0 ]]; then
    echo "WARN: $SVC_NAME is already running, PID is $PID."
  else
    echo "starting $SVC_NAME ..."
    rm -f ${PID_FILE}
     nohup ${RUN_JAVA} ${JAVA_OPTS} -Dserver.port=${SVC_PORT} -Dspring.profiles.active=${ENV} -jar ${SVC_DEP_HOME}/${SVC_JAR_NAME} ${PROGRAM_OPTS} > /dev/null 2>&1 &
    echo $! > ${PID_FILE}
    echo "$SVC_NAME has started, PID is $!."
  fi
}

# 停止
stop(){
  check_pid
  if [[ ${PID_FLAG} -ne 0 ]]; then
    echo "stoping $SVC_NAME ..."
    # 循环检查进程多次，每次睡眠等待2秒
    for((i=1;i<=${CHECK_COUNT};i++))
      do
        kill ${PID}
	    sleep 2
	    # 检查进程状态
	    check_pid
        if [[ ${PID_FLAG} -eq 0 ]]; then
          break
	    fi
      done
      # 如果以上多次正常关闭进程操作都失败，则强制关闭
      if [[ ${PID_FLAG} -ne 0 ]]; then
        echo "force the process to kill ..."
        kill -9 ${PID}
        sleep 2
      else
        echo "$SVC_NAME has stopped!"
    fi
  else
    echo "WARN: $SVC_NAME is not running."
  fi
}

# 输出运行状态
status(){
  check_pid
  if [[ ${PID_FLAG} -eq 0 ]]; then
    echo "$SVC_NAME is not running."
  else
    echo "$SVC_NAME is runing, PID is $PID."
  fi
}

# 重启
restart(){
  stop
  start
}

# 根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
   # 默认用 restart
    #usage
	restart
    ;;
esac

exit 0