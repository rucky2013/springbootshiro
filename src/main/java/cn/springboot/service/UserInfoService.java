/**   
 * @Title: UserInfoService.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28 
 * @version V1.0   
 */
package cn.springboot.service;

import cn.springboot.bean.UserInfo;

/** 
 * @ClassName: UserInfoService 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28  
 */
public interface UserInfoService {

	//通过username查找用户信息
	public UserInfo findByUsername(String username);
}
