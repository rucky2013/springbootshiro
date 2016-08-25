/**   
 * @Title: UserInfoRepository.java 
 * @Package cn.springboot.repository 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:45:57 
 * @version V1.0   
 */
package cn.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.springboot.bean.SysRole;

/** 
 * @ClassName: UserInfoRepository 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月11日 上午8:45:57  
 */
public interface SysRoleDao extends PagingAndSortingRepository<SysRole, Long>,JpaRepository<SysRole, Long>,JpaSpecificationExecutor{

}
