package com.dusksoft.dao;

import java.util.List;

import com.dusksoft.bean.UserBean;

public interface UserDao {

	public int insert(UserBean user);
	public boolean update(UserBean user, int id);
	public boolean delete(int id);
	public List<UserBean> findAll();
	public UserBean find(int id);
	
}
