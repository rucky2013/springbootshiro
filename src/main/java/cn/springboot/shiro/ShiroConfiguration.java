/**   
 * @Title: ShiroConfiguration.java 
 * @Package cn.springboot.shiro 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月10日 下午5:26:00 
 * @version V1.0   
 */
package cn.springboot.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ShiroConfiguration
 * @Description: Shiro配置 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc
 *               通过DispachServlet 来主控制一样。 既然是使用 Filter
 *               一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则 和访问权限。
 *               注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
 *               初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
 * @author SUN
 * @date 2016年8月10日 下午5:26:00
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * 
	 * @Title: shirFilter @Description: ShiroFilterFactoryBean 处理拦截资源文件问题
	 *         Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔
	 *         2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles @param
	 *         securityManager @return @throws
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出过滤器，具体退出代码Shiro已经实现了
		filterChainDefinitionMap.put("/logout", "logout");
		
		//配置记住密码或认证通过可以访问的地址
		filterChainDefinitionMap.put("/index", "user");//user级别rememberme可以直接访问
		filterChainDefinitionMap.put("/", "user");

		// 过滤链定义，从上向下顺序执行，一般将/**放置最下边
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fpage/**", "anon");
		filterChainDefinitionMap.put("/jquery-easyui-1.5/**", "anon");
		// authc:所有URL都必须认证通过才能访问；anon:所有URL都可以匿名访问
		filterChainDefinitionMap.put("/**", "authc");

		// 如果不设置默认会自动寻找web工程根目录下的“/login.jsp”页面
		shiroFilterFactoryBean.setLoginUrl("/login");

		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");

		// 未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	/**
	 * 
	 * @Title: myShiroRealm @Description: 身份认真realm @return @throws
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm
		securityManager.setRealm(myShiroRealm());
		// 注入缓存管理器
		securityManager.setCacheManager(ehCacheManager());
		//注入记住密码管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 
	 * @Title: hashedCredentialsMatcher @Description: 凭证匹配器 @return @throws
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于
								// md5(md5(""));
		return hashedCredentialsMatcher;
	}

	/**
	 * 
	 * @Title: advisor @Description: 开启Shiro aop注解,启用权限管理 @param
	 * securityManager @return @throws
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * 
	 * @Title: ehCacheManager @Description: 权限缓存 @return @throws
	 */
	@Bean
	public EhCacheManager ehCacheManager() {
		System.out.println("ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}

	/**
	 * 
	 * @Title: rememberMeCookie 
	 * @Description: cookie对象，用于记住密码
	 * @return
	 * @throws
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		System.out.println("ShiroConfigurationMeCookie()");
		// 这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// 记住密码时间30天，单位秒
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}
	
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

}
