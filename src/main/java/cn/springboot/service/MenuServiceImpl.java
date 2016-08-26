/**   
 * @Title: MenuServiceImpl.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author szc
 * @date 2016年8月24日 上午9:26:44 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.springboot.bean.SysPermission;
import cn.springboot.common.Tree;
import cn.springboot.dao.SysPermissionDao;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: MenuServiceImpl
 * @Description: TODO
 */
@Service
public class MenuServiceImpl implements MenuService {

	private String id;
	private String dataOptions;
	private String iconCls;
	private String style;
	private StringBuilder linkStr = null;

	@Resource
	private SysPermissionDao sysPermissionDao;

	@Override
	public String div() {
		linkStr = new StringBuilder();
		List<SysPermission> pmods = findModule((long) 0);
		if (!StringUtil.isEmpty(pmods) && pmods.size() > 0) {
			for (Object modsObj : pmods) {
				SysPermission mods = (SysPermission) modsObj;
				if ("false".equals(mods.getAvailable())) {
					return null;
				}
				if (!StringUtil.isEmpty(mods)) {
					linkStr.append("<div ");
					linkStr.append("title =\"");
					linkStr.append(mods.getName());
					linkStr.append("\"");
					if (!StringUtil.isEmpty(style)) {
						linkStr.append(" style=\"");
						linkStr.append(style);
						linkStr.append("\"");
					}
					if (!StringUtil.isEmpty(dataOptions)) {
						linkStr.append(" data-options=\"");
						linkStr.append(dataOptions);
						linkStr.append("\"");
					}
					if (null != id && id.length() > 0) {
						linkStr.append(" id =\"");
						linkStr.append(id);
						linkStr.append("\"");
					}
					if (null != iconCls && iconCls.length() > 0) {
						linkStr.append(" iconCls =\"");
						linkStr.append(iconCls);
						linkStr.append("\"");
					}
					linkStr.append(">");
					List<SysPermission> childmods = findModule(mods.getId());
					if (childmods != null && childmods.size() > 0) {
						for (Object cmodObj : childmods) {
							SysPermission cmod = (SysPermission) cmodObj;
							if ("false".equals(cmod.getAvailable())) {
								return null;
							}
							if ("menu".equals(cmod.getResourceType())) {
								linkStr.append("<div");
								submenu(cmod.getUrl(), cmod.getName());
							}
						}
						linkStr.append("</div>");
					}
					// linkStr.append("</div>");
				}
			}
		}
		return linkStr.toString();
	}

	private void submenu(String id, String text) {
		linkStr.append(" class=\"nav_item\">");
		linkStr.append("<a><span onclick=\"addTab(");
		if (null != id && id.length() > 0) {
			linkStr.append("'");
			linkStr.append(text);
			linkStr.append("','");
			linkStr.append(id);
			linkStr.append("')\"");

		}
		linkStr.append(">");
		if (null != text && text.length() > 0) {
			linkStr.append(text);
		} else {
			linkStr.append("未知按钮");
		}
		linkStr.append("</span></a></div>");
	}

	@Override
	public List<SysPermission> findModule(long parentId) {
		List<SysPermission> modList = sysPermissionDao.findByParentId(parentId);
		return modList;
	}

	@Override
	public List<Tree> findAllTree() {
		List<Tree> trees = new ArrayList<Tree>();
		// 查询所有的一级树
		List<SysPermission> resources = findModule(0);
		if (resources == null) {
			return null;
		}
		for (SysPermission resourceOne : resources) {
			Tree treeOne = new Tree();

			treeOne.setId(resourceOne.getId());
			treeOne.setText(resourceOne.getName());
			treeOne.setAttributes(resourceOne.getUrl());
			// 查询所有一级树下的菜单
			List<SysPermission> resourceSon = findModule(resourceOne.getId());

			if (resourceSon != null) {
				List<Tree> tree = new ArrayList<Tree>();
				for (SysPermission resourceTwo : resourceSon) {
					Tree treeTwo = new Tree();
					treeTwo.setId(resourceTwo.getId());
					treeTwo.setText(resourceTwo.getName());
					treeTwo.setAttributes(resourceTwo.getUrl());
					tree.add(treeTwo);
				}
				treeOne.setChildren(tree);
			} else {
				treeOne.setState("closed");
			}
			trees.add(treeOne);
		}
		return trees;
	}

	@Override
	public List<Tree> findAllTrees() {
		List<Tree> treeOneList = new ArrayList<Tree>();
		// 查询所有的一级树
		List<SysPermission> resources = findModule(0);
		if (resources == null) {
			return null;
		}
		for (SysPermission resourceOne : resources) {
			Tree treeOne = new Tree();

			treeOne.setId(resourceOne.getId());
			treeOne.setText(resourceOne.getName());
			treeOne.setAttributes(resourceOne.getUrl());

			List<SysPermission> resourceSon = findModule(resourceOne.getId());

			if (resourceSon == null) {
				treeOne.setState("closed");
			} else {
				List<Tree> treeTwoList = new ArrayList<Tree>();

				for (SysPermission resourceTwo : resourceSon) {
					Tree treeTwo = new Tree();

					treeTwo.setId(resourceTwo.getId());
					treeTwo.setText(resourceTwo.getName());
					treeTwo.setAttributes(resourceTwo.getUrl());

					/***************************************************/
					List<SysPermission> resourceSons = findModule( resourceTwo.getId());

					if (resourceSons == null) {
						treeTwo.setState("closed");
					} else {
						List<Tree> treeThreeList = new ArrayList<Tree>();

						for (SysPermission resourceThree : resourceSons) {
							Tree treeThree = new Tree();

							treeThree.setId(resourceThree.getId());
							treeThree.setText(resourceThree.getName());
							treeThree.setAttributes(resourceThree.getUrl());

							treeThreeList.add(treeThree);
						}
						treeTwo.setChildren(treeThreeList);
					}
					/***************************************************/

					treeTwoList.add(treeTwo);
				}
				treeOne.setChildren(treeTwoList);
			}

			treeOneList.add(treeOne);
		}
		return treeOneList;
	}
}
