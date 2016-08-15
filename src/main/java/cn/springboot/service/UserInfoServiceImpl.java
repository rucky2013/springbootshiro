/**   
 * @Title: UserInfoServiceImpl.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45 
 * @version V1.0   
 */
package cn.springboot.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.springboot.bean.UserInfo;
import cn.springboot.repository.UserInfoRepository;

/** 
 * @ClassName: UserInfoServiceImpl 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45  
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Resource
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo findByUsername(String username) {
		System.out.println("UserInfoServiceImpl.findByUsername()");
		return userInfoRepository.findByUsername(username);
	}

}
