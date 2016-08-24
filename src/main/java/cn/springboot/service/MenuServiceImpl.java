/**   
 * @Title: MenuServiceImpl.java 
 * @Package cn.springboot.service 
 * @Description: TODO
 * @author szc
 * @date 2016年8月24日 上午9:26:44 
 * @version V1.0   
 */
package cn.springboot.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.springboot.bean.SysPermission;
import cn.springboot.dao.SysPermissionDao;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: MenuServiceImpl
 * @Description: TODO
 * @author szc
 * @date 2016年8月24日 上午9:26:44
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
		System.out.println("父节点：" + parentId);
		List<SysPermission> modList = sysPermissionDao.findByParentId(parentId);
		return modList;
	}

}
