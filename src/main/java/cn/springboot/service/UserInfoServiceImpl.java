/**   
 * @Title: UserInfoServiceImpl.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import cn.springboot.bean.UserInfo;
import cn.springboot.dao.UserInfoDao;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public UserInfo findByUsername(String username) {
		System.out.println("UserInfoServiceImpl.findByUsername()");
		return userInfoDao.findByUsername(username);
	}


	@Override
	public void addUser(UserInfo userInfo) {
		userInfoDao.save(userInfo);		
	}


}
