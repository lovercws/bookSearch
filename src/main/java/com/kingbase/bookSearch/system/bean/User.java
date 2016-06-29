package com.kingbase.bookSearch.system.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 * @author ganliang
 */
public class User  implements Serializable{

	private static final long serialVersionUID = 8176583303644079703L;
	
	public final static String INIT_PASSWORD = "123456"; // 初始化的密码
	public final static String SESSION_USER = "user"; // 初始化的密码
	public final static String REGISTRY_VERIFYCODE = "registry_verifycode"; // 注册验证码
	public final static String LOGIN_VERIFYCODE = "login_verifycode"; // 登录验证码
	public static final String MAIL_VERIFYCODE = "mail_verifycode"; // 
	
	private int id;//主键
	private String name;//用户姓名
	private String password;//用户密码
	
	private String nickname;//用户昵称
	private String sex;//用户性别  1 2
	private String email;//用户邮箱
	
	private String mobile;//手机号码
	private String contactTel;//座机号码
	private String address;//联系地址
	
	
	private String remark;//用户备注说明
	private String headerImage;//用户头像
	private boolean online;//用户是否在线
	
	private Date birthday;//用户生日
	private Date registryDate;//用户注册日期
	private Date lastAccessDate;//用户最后访问时间
	
	private boolean active;//用户是否激活 可用
	
	private Set<Role> roles=new HashSet<Role>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHeaderImage() {
		return headerImage;
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getRegistryDate() {
		return registryDate;
	}
	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}
	public Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", nickname=" + nickname + ", sex="
				+ sex + ", email=" + email + ", mobile=" + mobile + ", contactTel=" + contactTel + ", address="
				+ address + ", remark=" + remark + ", headerImage=" + headerImage + ", online=" + online + ", birthday="
				+ birthday + ", registryDate=" + registryDate + ", lastAccessDate=" + lastAccessDate + ", active="
				+ active + ", roles=" + roles + "]";
	}
	
	/**
	 * 非持久化字段
	 */
	private String verifyCode;//验证码
	private String newPassword;//新密码

	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
