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
import cn.springboot.bean.UserInfo;

/**
 * @ClassName: UserInfoService
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:48:28
 */
public interface SysPermissionService {


	// 通过id查找信息
	public SysPermission findById(long id);
	//新增
	public void addPermission(SysPermission sysPermission);
	//编辑
	public SysPermission updatePermission(SysPermission sysPermission);
	//分页条件查询
	public Map<String, Object> getSysPermissionByPageable(String queryString,Integer page,Integer rows);
	//删除
	public void deleteSysPermission(String[] ids) throws Exception;
	
}
