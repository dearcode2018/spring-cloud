# ----- 信息 -----
# @filename start.sh
# @version 1.0
# @author qianye.zheng
# @description  启动脚本

# 解决远程ssh执行脚本失败问题
source /etc/profile

# 进入脚本所在目录
cd `dirname $0`

# 变量定义
# 应用名称
declare -r APP_NAME=""
declare -r APP_VERSION="1.0"
# (Jar包)文件名
declare -r FILE_NAME="${APP_NAME}-${APP_VERSION}.jar"
# 应用路径
declare -r HOME_PATH="/usr/local/${APP_NAME}"
# 日志路径
declare -r LOG_PATH="/data/logs/${APP_NAME}"
# 应用端口
declare -r APP_PORT=7070
# 程序参数
declare -r PROGRAM_OPTS=""
# 启动命令
declare -r RUN_JAVA='java'
# 进程ID文件，启动多个进程则用不同的pid文件
PID_FILE="process.pid"
# 检查次数
CHECK_COUNT=3

# 创建目录
mkdir -p ${HOME_PATH}
mkdir -p ${LOG_PATH}

# 进入应用所在目录（虽然都是绝对路径，但有些应用需要进入应用目录才能启动成功）
cd ${HOME_PATH}

# JVM 参数配置，-Xms最小内存，-Xmx最大内存
JAVA_OPTS="-Xms256m -Xmx512m \
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
-Xloggc:$LOG_PATH/gc.log \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=$LOG_PATH/dump.logs"

# 进程状态标识变量，0-不存在, 1-存在
PID_FLAG=0

# 使用说明，用来提示输入参数
usage() {
    echo "usage: sh $0 [start | stop | restart | status]"
    exit 1
}

# 检查进程状态
check_pid() {
  if [[ -f "$PID_FILE" ]]; then
    PID=`cat "$PID_FILE" | awk '{print $1}'`
	# 根据进程ID检查该进程是否确实存在
    PID=`ps -aef | grep "$PID" | awk '{print $2}' | grep "$PID"`
    if [[ -z "$PID" ]]; then # -z 字符串长度为0，说明该进程不存在
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
start() {
  check_pid
  if [[ ${PID_FLAG} -ne 0 ]]; then
    echo "WARN: ${APP_NAME} is already running, PID is ${PID}."
  else
    echo "starting ${APP_NAME} ..."
    rm -f ${PID_FILE}
     nohup ${RUN_JAVA} ${JAVA_OPTS} -Dserver.port=${APP_PORT} -jar ${HOME_PATH}/${FILE_NAME} ${PROGRAM_OPTS} > /dev/null 2>&1 &
	 # 将当前原生命令的执行的进程ID写入文件，调用其他脚本则无需写，一般目标脚本都会生成进程ID文件
    echo $! > ${PID_FILE}
    echo "${APP_NAME} has started, PID is $!."
  fi
}

# 停止
stop() {
  check_pid
  if [[ ${PID_FLAG} -ne 0 ]]; then
    echo "stoping ${APP_NAME} ..."
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
        echo "${APP_NAME} has stopped!"
    fi
  else
    echo "WARN: ${APP_NAME} is not running."
  fi
}

# 输出运行状态
status() {
  check_pid
  if [[ ${PID_FLAG} -eq 0 ]]; then
    echo "${APP_NAME} is not running."
  else
    echo "${APP_NAME} is runing, PID is $PID."
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