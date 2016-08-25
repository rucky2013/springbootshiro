/**   
 * @Title: MenuService.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author szc
 * @date 2016年8月24日 上午9:25:35 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.List;

import cn.springboot.bean.SysPermission;
import cn.springboot.common.Tree;

/**
 * @ClassName: MenuService
 * @Description: TODO
 * @author szc
 * @date 2016年8月24日 上午9:25:35
 */
public interface MenuService {

	public String div();

	// 根据父栏目编号获取栏目信息的集合，其中"List<SysPermission>"中存放的是SysPermission实体
	public List<SysPermission> findModule(long parentId);
	//生成菜单树，不带按钮
	public List<Tree> findAllTree();
	//生成菜单树，带按钮
	public List<Tree> findAllTrees();
}
