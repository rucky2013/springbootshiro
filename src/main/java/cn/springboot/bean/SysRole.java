/**   
 * @Title: SysRole.java 
 * @Package cn.springboot.bean 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月10日 下午7:18:47 
 * @version V1.0   
 */
package cn.springboot.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @ClassName: SysRole
 * @Description: 系统角色实体类
 * @author SUN
 * @date 2016年8月10日 下午7:18:47
 */
@Entity
public class SysRole implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;// 编号

	private String role;// 角色标识程序中判断使用,如"admin",这个是唯一的

	private String description; // 角色描述，UI显示使用

	private Boolean available = Boolean.FALSE;// 是否可用，不可用不会添加给用户

	// 角色--权限关系：多对多
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SysRolePermission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "permissionId") })
	private List<SysPermission> permission;

	// 用户 - 角色关系定义;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "uid") })
	private List<UserInfo> userInfos;// 一个角色对应多个用户

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SysPermission> getPermission() {
		return permission;
	}

	public void setPermission(List<SysPermission> permission) {
		this.permission = permission;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", role=" + role + ", description=" + description + ", available="
				+ available + "]";
	}

}
