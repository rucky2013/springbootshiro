/**   
 * @Title: MyShiroRealm.java 
 * @Package cn.springboot.shiro 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:54:16 
 * @version V1.0   
 */
package cn.springboot.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.springboot.bean.UserInfo;
import cn.springboot.service.UserInfoService;
import cn.springboot.util.StringUtil;
import cn.springboot.bean.SysPermission;
import cn.springboot.bean.SysRole;

/**
 * @ClassName: MyShiroRealm
 * @Description: 身份校验核心类
 * @author SUN
 * @date 2016年8月11日 上午8:54:16
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 此方法调用  hasRole,hasPermission的时候才会进行回调.      *      *
	 * 权限信息.(授权):      * 1、如果用户正常退出，缓存自动清空；      * 2、如果用户非正常退出，缓存自动清空；    
	 *  * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。      *
	 * （需要手动编程进行实现；放在service进行调用）      *
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，      *
	 * 调用clearCached方法；      * :Authorization
	 * 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。      * @param
	 * principals      * @return      
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		 *当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
		 * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
		 * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了
		 * 缓存过期之后会再次执行。         
		 */
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo)principals.getPrimaryPrincipal();
		//String userName = (String) principals.getPrimaryPrincipal();
		//UserInfo userInfo=userInfoService.findByUsername(userName);
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

		for(SysRole role:userInfo.getRoleList()){
			authorizationInfo.addRole(role.getRole());
			for(SysPermission p : role.getPermission()){
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}

		//设置权限信息.
		//     authorizationInfo.setStringPermissions(getStringPermissions(userInfo.getRoleList()));
		System.out.println("权限信息："+ReflectionToStringBuilder.toString(authorizationInfo, ToStringStyle.MULTI_LINE_STYLE));
		return authorizationInfo;
	}

	/**
	 * 认证信息，身份验证 Authentication是用来验证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthorizationInfo()");

		// 获取用户输入的帐号
		String username = (String) token.getPrincipal();
		
		System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		
		// 通过username从数据库中查找User对象，如果找到
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo userInfo = userInfoService.findByUsername(username);
		System.out.println("---------->>userInfo=" + userInfo);
		if (userInfo == null) {
			return null;
		}
		
		if(userInfo.getState()==1){
			throw new DisabledAccountException();
		}
		/*
		 * 获取权限信息:这里没有进行实现 请自行根据UserInfo,Role,Permission进行实现
		 * 获取之后可以在前端for循环显示所有链接;         
		 */
		// userInfo.setPermissions(userService.findPermissions(user));
		// 账号判断;
		// 加密方式;
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，也可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo,// 用户名
				userInfo.getPassword(),// 密码
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);

		// 明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
               /*		SimpleAuthenticationInfo authenticationInfo = new	 SimpleAuthenticationInfo(
					userInfo.getUsername(), //用户名
					userInfo.getPassword(), //密码
					getName() //realm name
					);*/
		return authenticationInfo;
	}

}
