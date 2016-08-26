/**   
 * @Title: UserInfoController.java 
 * @Package cn.springboot.controller 
 * @Description: TODO
 * @author szc
 * @date 2016年8月12日 上午10:31:09 
 * @version V1.0   
 */
package cn.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springboot.bean.SysRole;
import cn.springboot.bean.UserInfo;
import cn.springboot.service.SysRoleService;
import cn.springboot.service.UserInfoService;
import cn.springboot.util.StringUtil;

/**
 * @ClassName: UserInfoController
 * @Description: TODO
 * @author szc
 * @date 2016年8月12日 上午10:31:09
 */

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	private Map<String, Object> map = new HashMap<String, Object>();
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 
	 * @Title: userInfo @Description: 用户查询 @return @throws
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userInfo:list")
	public String userInfo() {
		return "userinfo/list";
	}

	/**
	 * 
	 * @Title: userInfo @Description: TODO @return @throws
	 */
	@RequestMapping("/listDetail")
	@ResponseBody
	public String listDetail(HttpServletRequest req, Integer page, Integer rows) {

		String queryString = req.getParameter("queryString");
		System.out.println("查询条件：" + queryString);
		return StringUtil.toJson(userInfoService.getUserByPageable(queryString, page, rows));
	}

	@RequestMapping("/add")
	@RequiresPermissions("userInfo:add")
	public String userAddPage() {
		return "userinfo/add";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest req, UserInfo user) {
		try {
			user.setPassword(StringUtil.password(user.getUsername(),"111111"));
			userInfoService.addUser(user);
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
	@RequiresPermissions("userInfo:del")
	@ResponseBody
	public String delete(HttpServletRequest req, String ids) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(ids)) {
			String[] id = ids.split(",");
			if (!StringUtil.isEmpty(id) && id.length > 0) {
				try {
					userInfoService.deleteUser(id);
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
	@RequiresPermissions("userInfo:edit")
	public String userEdit(HttpServletRequest req, Integer rowid) {
		UserInfo user = userInfoService.findByUid(rowid);
		List<SysRole> roles = user.getRoleList();
		StringBuilder str = new StringBuilder();
		if (roles != null) {
			for (SysRole role : roles) {
				str.append(role.getId() + ",");
			}
		}
		String temp = null;
		if (!StringUtil.isEmpty(str) && str.length() > 0) {
			temp = str.substring(0, str.length() - 1);
		} else {
			temp = str.toString();
		}
		req.setAttribute("roles", temp);
		req.setAttribute("user", user);
		System.out.println(req.getAttribute("user").toString());
		return "userinfo/edit";
	}

	@RequestMapping("userUpdate")
	@ResponseBody
	public String updateUser(HttpServletRequest req, UserInfo user) {
		try {
			if (!StringUtil.isEmpty(user.getUid())) {
				userInfoService.updateUser(user);
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
	
	@RequestMapping(value = "/getRoles")
	@ResponseBody
	public String getRoleList(HttpServletRequest req) throws Exception {
		return StringUtil.toJson(sysRoleService.getAllRoles());
	}
}
