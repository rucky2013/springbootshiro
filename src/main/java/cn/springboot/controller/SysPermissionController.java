/**   
 * @Title: SysPermissionController.java 
 * @Package cn.springboot.controller 
 * @Description: TODO
 * @author szc
 * @date 2016年8月25日 上午9:12:12 
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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springboot.bean.SysPermission;
import cn.springboot.service.SysPermissionService;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: SysPermissionController
 * @Description: TODO
 * @author szc
 * @date 2016年8月25日 上午9:12:12
 */

@Controller
@RequestMapping("/sysper")
public class SysPermissionController {

	private Map<String, Object> map = new HashMap<String, Object>();
	@Resource
	private SysPermissionService sysPermissionService;

	@RequestMapping("/list")
	@RequiresPermissions("permission:list")
	public String list() {
		return "permission/list";
	}

	@RequestMapping("/listPermission")
	@ResponseBody
	public String listPermission(HttpServletRequest req, Integer page, Integer rows) {

		String queryString = req.getParameter("queryString");
		System.out.println("查询条件：" + queryString);
		return StringUtil.toJson(sysPermissionService.getSysPermissionByPageable(queryString, page, rows));
	}

	@RequestMapping("/add")
	@RequiresPermissions("permission:add")
	public String add() {
		return "permission/add";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest req, SysPermission permission) {
		try {
			sysPermissionService.addPermission(permission);
			map.put("success", true);
			map.put("msg", "信息提交成功");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "提示:" + e.getMessage());
			e.printStackTrace();
		}

		return StringUtil.toJson(map);
	}

	@RequestMapping("del")
	@RequiresPermissions("permission:del")
	@ResponseBody
	public String delete(HttpServletRequest req, String ids) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(ids)) {
			String[] id = ids.split(",");
			if (!StringUtil.isEmpty(id) && id.length > 0) {
				try {
					sysPermissionService.deleteSysPermission(id);
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
	@RequiresPermissions("permission:edit")
	public String edit(HttpServletRequest req, Integer rowid) {
		System.out.println("资源id:" + rowid);
		SysPermission permission = sysPermissionService.findById(rowid);
		req.setAttribute("permission", permission);
		System.out.println(req.getAttribute("permission").toString());
		return "permission/edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public String update(HttpServletRequest req, SysPermission permission) {
		try {
			if (!StringUtil.isEmpty(permission.getId())) {
				sysPermissionService.updatePermission(permission);
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
