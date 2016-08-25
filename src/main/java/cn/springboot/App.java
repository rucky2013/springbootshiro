package cn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class App{
	// 嵌入容器部署
	public static void main(String[] args) throws Exception {
		//SpringApplication.run(App.class, args);
		System.setProperty("spring.devtools.restart.enabled", "true"); //是否自动重启
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		
	}
}
