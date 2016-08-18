/**   
 * @Title: StringUtil.java 
 * @Package cn.springboot.util 
 * @Description: TODO
 * @author szc
 * @date 2016年8月18日 上午8:56:25 
 * @version V1.0   
 */
package cn.springboot.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: StringUtil
 * @Description: TODO
 * @author szc
 * @date 2016年8月18日 上午8:56:25
 */
public class StringUtil {
	public static ObjectMapper objectMapper;

	/**
	 * 转换Json数据
	 * @param obj
	 * @return
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
