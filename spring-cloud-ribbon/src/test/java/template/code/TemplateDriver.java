/**
 * 描述: 
 * TemplateDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package template.code;


/**
 * 描述:  - 驱动器
 * @author  qye.zheng
 * TemplateDriver
 */
public class TemplateDriver
{
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private TemplateDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean template()
	{
		boolean flag = false;
		try
		{
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
