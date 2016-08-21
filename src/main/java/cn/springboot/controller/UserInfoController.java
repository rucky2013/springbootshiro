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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springboot.bean.UserInfo;
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

	/**
	 * 
	 * @Title: userInfo @Description: 用户查询 @return @throws
	 */
	@RequestMapping("/userList")
	@RequiresPermissions("userInfo:view")
	public String userInfo() {
		return "userinfo/list";
	}

	/**
	 * 
	 * @Title: userInfo @Description: TODO @return @throws
	 */
	@RequestMapping("/listDetail")
	@RequiresPermissions("userInfo:listdetail")
	@ResponseBody
	public String listDetail(HttpServletRequest req, Integer page, Integer rows) {

		String queryString = req.getParameter("queryString");
		System.out.println("查询条件：" + queryString);
		return StringUtil.toJson(userInfoService.getUserByPageable(queryString, page, rows));
	}

	@RequestMapping("/userAddPage")
	@RequiresPermissions("userInfo:add")
	public String userAddPage() {
		return "userinfo/add";
	}

	@RequestMapping("userAdd")
	@ResponseBody
	public String save(HttpServletRequest req, UserInfo user) {
		try {
			user.setPassword("456");
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

	@RequestMapping("userDelOne")
	@RequiresPermissions("userInfo:delOne")
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

	@RequestMapping("userEdit/{Id}")
	@RequiresPermissions("userInfo:edit")
	public String userEdit(HttpServletRequest req, @PathVariable("Id") Integer Id) {
		System.out.println("用户id:" + Id);
		UserInfo user = userInfoService.findByUid(Id);
		req.setAttribute("user", user);
		return "userinfo/edit";
	}

	@RequestMapping("userUpdate")
	public String updateUser(HttpServletRequest req, UserInfo userInfo) {
		try {
			if (!StringUtil.isEmpty(userInfo.getUid())) {
				userInfoService.updateUser(userInfo);
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
