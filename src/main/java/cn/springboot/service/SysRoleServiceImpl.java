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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.springboot.bean.SysRole;
import cn.springboot.dao.SysRoleDao;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:50:45
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;

	/**
	 * 新增
	 */
	@Override
	public void addRole(SysRole role) {
		sysRoleDao.save(role);
	}

	/**
	 * 分页查询 queryString查询条件
	 */
	@Override
	public Map<String, Object> getRoleByPageable(String queryString, Integer page, Integer rows) {
		// 当前页，每页条数
		Pageable pageable = new PageRequest(page - 1, rows);
		// 查询条件
		Specification<SysRole> spec = new Specification<SysRole>() {
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (queryString != null && queryString.trim().length() > 0) {
					list.add(cb.like(root.get("name").as(String.class), "%" + queryString + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Page<SysRole> role = sysRoleDao.findAll(spec, pageable);

		Map<String, Object> map = new HashMap<String, Object>();
		List<SysRole> list = new ArrayList<SysRole>();
		List<SysRole> lists = role.getContent();
		if (lists != null) {
			for (Object obj : lists) {
				SysRole us = new SysRole();
				SysRole u = (SysRole) obj;
				us.setId(u.getId());
				us.setRole(u.getRole());
				us.setAvailable(u.getAvailable());
				us.setDescription(u.getDescription());
				list.add(us);
			}
		}
		map.put("total", role.getTotalElements());
		map.put("rows", list);
		return map;
	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteRole(String[] ids) throws Exception {
		if (!StringUtil.isEmpty(ids) && ids.length > 0) {
			for (String id : ids) {
				if (!StringUtil.isEmpty(id)) {
					SysRole roleDel= sysRoleDao.findOne(Long.parseLong(id));
					sysRoleDao.delete(roleDel);
				}
			}
		}
	}

	/**
	 * 编辑用户信息
	 */
	@Override
	public SysRole updateRole(SysRole role) {
		SysRole roleUpdate = sysRoleDao.findOne(role.getId());
		if (role.getRole() != null) {
			roleUpdate.setRole(role.getRole());
			roleUpdate.setAvailable(role.getAvailable());
			roleUpdate.setDescription(role.getDescription());
		}
		sysRoleDao.save(roleUpdate);
		return roleUpdate;
	}

	@Override
	public SysRole findById(long id) {
		return sysRoleDao.findOne(id);
	}
}
