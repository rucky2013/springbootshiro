/**   
 * @Title: UserInfo.java 
 * @Package cn.springboot.bean 
 * @Description: TODO
 * @author SUN
 * @date 2016年8月10日 下午5:52:37 
 * @version V1.0   
 */
package cn.springboot.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @ClassName: UserInfo
 * @Description: 用户信息
 * @author SUN
 * @date 2016年8月10日 下午5:52:37
 */
@Entity
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long uid; // 用户id

	@Column(unique = true)
	private String username; // 用户名

	private String name; // 名称

	private String password;// 密码
	
	private String salt;//加密密码的盐
	
	private byte state;//用户状态 0:创建未认证 1:正常状态 2:用户被锁定
	
	@ManyToMany(fetch=FetchType.EAGER) //立即从数据库中进行加载数据
	@JoinTable(name="SysUserRole" ,joinColumns={@JoinColumn(name="uid")},inverseJoinColumns={@JoinColumn(name = "roleId") })
	private List<SysRole> roleList; //一个用户多个角色

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", salt=" + salt + ", state=" + state + "]";
	}
	
	
	/**
	 * 
	 * @Title: getCredentialsSalt 
	 * @Description: 密码盐
	 * @return
	 * @throws
	 */
	public String getCredentialsSalt(){
		return this.username+this.salt;
	}

}
