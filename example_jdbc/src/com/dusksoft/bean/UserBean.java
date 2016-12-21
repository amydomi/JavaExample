package com.dusksoft.bean;

public class UserBean {
	
	private int id;
	private String userName;
	private String password;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "id:\t\t" + id + "\r\nusername:\t" + userName + "\r\npassword:\t" + password + "\r\n";
	}
}
