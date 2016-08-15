/**   
 * @Title: LogUtil.java 
 * @Package cn.springboot.util 
 * @Description: TODO
 * @author szc
 * @date 2016年8月15日 下午5:19:52 
 * @version V1.0   
 */
package cn.springboot.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: LogUtil 
 * @Description: TODO
 * @author szc
 * @date 2016年8月15日 下午5:19:52  
 */
public class LogUtil {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test(){
		logger.debug("debug");
		logger.trace("trace");
		logger.info("info");
	}
}
