/**   
 * @Title: UserInfoRepository.java 
 * @Package cn.springboot.repository 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:45:57 
 * @version V1.0   
 */
package cn.springboot.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.springboot.bean.UserInfo;

/** 
 * @ClassName: UserInfoRepository 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:45:57  
 */
public interface UserInfoDao extends PagingAndSortingRepository<UserInfo, String>,JpaSpecificationExecutor{

	//通过username查找用户信息
	public UserInfo findByUsername(String username);

}
