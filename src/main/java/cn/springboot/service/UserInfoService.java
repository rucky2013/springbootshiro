/**   
 * @Title: UserInfoService.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.Map;

import cn.springboot.bean.UserInfo;

/**
 * @ClassName: UserInfoService
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28
 */
public interface UserInfoService {

	// 通过username查找用户信息
	public UserInfo findByUsername(String username);
	// 通过id查找用户信息
	public UserInfo findByUid(long uid);
	//新增用户
	public void addUser(UserInfo userInfo);
	//编辑用户信息
	public UserInfo updateUser(UserInfo userInfo);
	//分页条件查询
	public Map<String, Object> getUserByPageable(String queryString,Integer page,Integer rows);
	//删除
	public void deleteUser(String[] ids) throws Exception;
	
	

}
