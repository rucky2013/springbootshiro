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

import cn.springboot.bean.SysPermission;
import cn.springboot.bean.UserInfo;
import cn.springboot.dao.SysPermissionDao;
import cn.springboot.dao.UserInfoDao;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Resource
	private SysPermissionDao sysPermissionDao;

	/**
	 * 新增
	 */
	@Override
	public void addPermission(SysPermission sysPermission) {
		sysPermissionDao.save(sysPermission);
	}

	/**
	 * 分页查询 queryString查询条件
	 */
	@Override
	public Map<String, Object> getSysPermissionByPageable(String queryString, Integer page, Integer rows) {
		// 排序
		Sort sort = new Sort(Sort.Direction.DESC, "name").and(new Sort(Sort.Direction.ASC, "id"));
		// 当前页，每页条数
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		// 查询条件
		Specification<SysPermission> spec = new Specification<SysPermission>() {
			public Predicate toPredicate(Root<SysPermission> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (queryString != null && queryString.trim().length() > 0) {
					list.add(cb.like(root.get("name").as(String.class), "%" + queryString + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Page<SysPermission> permission = sysPermissionDao.findAll(spec, pageable);

		Map<String, Object> map = new HashMap<String, Object>();
		List<SysPermission> list = new ArrayList<SysPermission>();
		List<SysPermission> lists = permission.getContent();
		if (lists != null) {
			for (Object obj : lists) {
				SysPermission us = new SysPermission();
				SysPermission u = (SysPermission) obj;
				us.setId(u.getId());
				us.setName(u.getName());
				us.setAvailable(u.getAvailable());
				us.setPermission(u.getPermission());
				us.setUrl(u.getUrl());
				us.setResourceType(u.getResourceType());
				us.setParentId(u.getParentId());
				list.add(us);
			}
		}
		map.put("total", permission.getTotalElements());
		map.put("rows", list);
		return map;
	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteSysPermission(String[] ids) throws Exception {
		if (!StringUtil.isEmpty(ids) && ids.length > 0) {
			for (String id : ids) {
				if (!StringUtil.isEmpty(id)) {
					SysPermission permissionDel = sysPermissionDao.findOne(Long.parseLong(id));
					sysPermissionDao.delete(permissionDel);
				}
			}
		}
	}

	/**
	 * 编辑用户信息
	 */
	@Override
	public SysPermission updatePermission(SysPermission sysPermission) {
		SysPermission permissionUpdate = sysPermissionDao.findOne(sysPermission.getId());
		System.out.println("权限："+sysPermission.toString());
		if (sysPermission.getName() != null) {
			permissionUpdate.setName(sysPermission.getName());
			permissionUpdate.setAvailable(sysPermission.getAvailable());
			permissionUpdate.setParentId(sysPermission.getParentId());
			permissionUpdate.setPermission(sysPermission.getPermission());
			permissionUpdate.setResourceType(sysPermission.getResourceType());
			permissionUpdate.setUrl(sysPermission.getUrl());
		}
		sysPermissionDao.save(permissionUpdate);
		return permissionUpdate;
	}

	@Override
	public SysPermission findById(long id) {
		return sysPermissionDao.findOne(id);
	}
}
