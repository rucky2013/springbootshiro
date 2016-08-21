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
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object object) {
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

	/**
	 * 判断对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if (!(null != str && !"".equals(str))) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (!(null != str && str.trim().length() > 0)) {
			return true;
		}
		return false;
	}


}
