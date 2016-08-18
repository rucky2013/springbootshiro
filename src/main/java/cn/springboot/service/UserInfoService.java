/**   
 * @Title: UserInfoService.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28 
 * @version V1.0   
 */
package cn.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
	
	public void addUser(UserInfo userInfo);
	
}
