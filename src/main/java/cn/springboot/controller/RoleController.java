/**   
 * @Title: RoleController.java 
 * @Package cn.springboot.controller 
 * @Description: TODO
 * @author szc
 * @date 2016年8月25日 下午3:25:10 
 * @version V1.0   
 */
package cn.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springboot.bean.SysRole;
import cn.springboot.service.MenuService;
import cn.springboot.service.SysRoleService;
import cn.springboot.util.StringUtil;

/** 
 * @ClassName: RoleController 
 * @Description: TODO
 * @author szc
 * @date 2016年8月25日 下午3:25:10  
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private SysRoleService sysRoleService;
	
	@RequestMapping("list")
	@RequiresPermissions("role:list")
	public String list(){
		return "role/list";
	}
	
	/**
	 * @Title: allTrees 
	 * @Description: 所有菜单和功能
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "allTrees", method = RequestMethod.POST)
	@ResponseBody
	public Object allTrees() {
		return menuService.findAllTrees();
	}


	//添加角色页面
	@RequestMapping("add")
	@RequiresPermissions("role:add")
	public String add(){
		return "role/add";
	}
	//保存
	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest req, SysRole role) {
		try {
			sysRoleService.addRole(role);
			map.put("success", true);
			map.put("msg", "信息提交成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "提示:" + e.getMessage());
			e.printStackTrace();
		}

		return StringUtil.toJson(map);
	}
	//删除
	@RequestMapping("del")
	@RequiresPermissions("role:del")
	@ResponseBody
	public String delete(HttpServletRequest req, String ids) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(ids)) {
			String[] id = ids.split(",");
			if (!StringUtil.isEmpty(id) && id.length > 0) {
				try {
					sysRoleService.deleteRole(id);
					map.put("success", true);
					map.put("msg", "信息删除成功");
				} catch (Exception e) {
					map.put("success", false);
					map.put("msg", "提示:" + e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			map.put("success", false);
			map.put("msg", "请选择删除的信息");
		}
		return StringUtil.toJson(map);
	}
	
	@RequestMapping("edit")
	@RequiresPermissions("role:edit")
	public String edit(HttpServletRequest req, Integer rowid) {
		SysRole role = sysRoleService.findById(rowid);
		req.setAttribute("role", role);
		System.out.println(req.getAttribute("role").toString());
		return "role/edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest req, SysRole role) {
		try {
			if (!StringUtil.isEmpty(role.getId())) {
				sysRoleService.updateRole(role);
				map.put("success", true);
				map.put("msg", "信息保存成功");
			} else {
				map.put("success", false);
				map.put("msg", "Null");
			}
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "提示:" + e.getMessage());
			e.printStackTrace();
		}
		return StringUtil.toJson(map);
	}
}
