/**   
 * @Title: SpringTest.java 
 * @Package cn.springboot 
 * @Description: TODO
 * @author szc
 * @date 2016年8月19日 下午4:59:46 
 * @version V1.0   
 */
package cn.springboot;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/** 
 * @ClassName: SpringTest 
 * @Description: TODO
 * @author szc
 * @date 2016年8月19日 下午4:59:46  
 */
public class SpringTest {
	
	@Test
	public void passwordTest(){
		String password =  new SimpleHash("md5","123456",ByteSource.Util.bytes("admin8d78869f470951332959580424d4bf4f"),2).toHex(); 
		System.out.println(password);
	}

}
