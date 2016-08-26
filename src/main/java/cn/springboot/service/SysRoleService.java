/**   
 * @Title: UserInfoService.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.List;
import java.util.Map;

import cn.springboot.bean.SysPermission;
import cn.springboot.bean.SysRole;

/**
 * @ClassName: UserInfoService
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28
 */
public interface SysRoleService {


	// 通过id查找信息
	public SysRole findById(long id);
	//新增
	public void addRole(SysRole role);
	//编辑
	public SysRole updateRole(SysRole role);
	//分页条件查询
	public Map<String, Object> getRoleByPageable(String queryString,Integer page,Integer rows);
	//删除
	public void deleteRole(String[] ids) throws Exception;
	
	public List<Map<String, Object>> getAllRoles();
	
}
