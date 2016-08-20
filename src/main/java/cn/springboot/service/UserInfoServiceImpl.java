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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.springboot.bean.UserInfo;
import cn.springboot.dao.UserInfoDao;
import cn.springboot.util.StringUtil;

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

	public Map<String, Object> getUserByPageable(String queryString, Integer page, Integer rows) {
		// 排序
		Sort sort = new Sort(Sort.Direction.DESC, "username").and(new Sort(Sort.Direction.ASC, "uid"));
		// 当前页，每页条数
		Pageable pageable = new PageRequest(page - 1, rows, sort);

		Specification<UserInfo> spec = new Specification<UserInfo>() {
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (queryString != null && queryString.trim().length() > 0) {
					list.add(cb.like(root.get("username").as(String.class), "%" + queryString + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Page<UserInfo> users = userInfoDao.findAll(spec, pageable);

		Map<String, Object> map = new HashMap<String, Object>();
		List<UserInfo> list = new ArrayList<UserInfo>();
		List<UserInfo> lists = users.getContent();
		if (lists != null) {
			for (Object obj : lists) {
				UserInfo us = new UserInfo();
				UserInfo u = (UserInfo) obj;
				us.setUsername(u.getUsername());
				us.setUid(u.getUid());
				list.add(us);
			}
		}
		map.put("total", users.getTotalElements());
		map.put("rows", list);
		return map;
	}

	// 删除记录
	@Override
	public void delete(String[] ids) throws Exception {
		if (!StringUtil.isEmpty(ids) && ids.length > 0) {
			for (String id : ids) {
				if (!StringUtil.isEmpty(id)) {
					userInfoDao.delete(Long.parseLong(id));;
				}
			}
		}
	}

	@Override
	public void deleteUser(int uid) throws Exception {
		UserInfo userDel=userInfoDao.findOne((long) uid);
		if(userDel==null){
			
		}
		userInfoDao.delete(userDel);
	}

	@Override
	public UserInfo updateUser(UserInfo userInfo) {
		UserInfo userUpdate=userInfoDao.findOne(userInfo.getUid());
		if(userInfo.getUsername()!=null){
			userUpdate.setUsername(userInfo.getUsername());
		}
		userInfoDao.save(userUpdate);
		return userUpdate;
	}
	

}
