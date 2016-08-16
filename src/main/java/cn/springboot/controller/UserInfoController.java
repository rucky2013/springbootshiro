/**   
 * @Title: UserInfoController.java 
 * @Package cn.springboot.controller 
 * @Description: TODO
 * @author szc
 * @date 2016年8月12日 上午10:31:09 
 * @version V1.0   
 */
package cn.springboot.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: UserInfoController 
 * @Description: TODO
 * @author szc
 * @date 2016年8月12日 上午10:31:09  
 */

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	
	/**
	 * 
	 * @Title: userInfo 
	 * @Description: 用户查询
	 * @return
	 * @throws
	 */
	@RequestMapping("/userList")
	@RequiresPermissions("userInfo:view")
	public String userInfo(){
		return "userinfo/list";
	}
	
	
	@RequestMapping("userAdd")
	@RequiresPermissions("userInfo:add")
	public String userInfoAdd(){
		return "userInfoAdd";
	}

	@RequestMapping("userDel")
	@RequiresPermissions("userInfo:del")
	public String userInfoDel(){
		return "userInfoDel";
	}
}
