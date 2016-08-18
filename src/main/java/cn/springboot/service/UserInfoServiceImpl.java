/**   
 * @Title: UserInfoServiceImpl.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
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

	public Map<String, Object> getUserByPageable() {
		//userInfoDao.findAll(pageable);
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo user=new UserInfo();
		user.setName("admin");
		user.setPassword("456");
		list.add(user);
		user.setName("abc");
		user.setPassword("123");
		list.add(user);
		map.put("total", 100);
		map.put("rows", list);
		return map;
	}

}
