/**   
 * @Title: AppInit.java 
 * @Package cn.springboot 
 * @Description: TODO
 * @author szc
 * @date 2016年8月12日 下午3:05:44 
 * @version V1.0   
 */
package cn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/** 
 * @ClassName: AppInit 
 * @Description: 部署到JavaEE容器
 * @author szc
 * @date 2016年8月12日 下午3:05:44  
 */
public class AppInit extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(AppInit.class, args);
	}
}
