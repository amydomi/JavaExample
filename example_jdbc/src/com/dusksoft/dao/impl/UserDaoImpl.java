package com.dusksoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dusksoft.bean.UserBean;
import com.dusksoft.dao.UserDao;
import com.dusksoft.util.DbUtil;

public class UserDaoImpl implements UserDao {
	
	@Override
	public int insert(UserBean user) {
		Connection conn = DbUtil.getConnection();
		String sql = "insert into users(username, password) values(?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getPassword());
			int row = psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();
			if(rs.next()) {
				int key = rs.getInt(row);
				return key;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(conn);
		}
		return 0;
	}

	@Override
	public boolean update(UserBean user, int id) {
		Connection conn = DbUtil.getConnection();
		String sql = "update users set username=?, password=? where id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getPassword());
			psmt.setInt(3, id);
			psmt.executeUpdate();
			return psmt.getUpdateCount() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(conn);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = DbUtil.getConnection();
		String sql = "delete from users where id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			int count = psmt.getUpdateCount();
			return count > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(conn);
		}
		return false;
	}

	@Override
	public List<UserBean> findAll() {
		Connection conn = DbUtil.getConnection();
		String sql = "select * from users order by id asc";
		try {
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<UserBean> dataList = new ArrayList<UserBean>();
			while(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				dataList.add(user);
			}
			return dataList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(conn);
		}
		return null;
	}

	@Override
	public UserBean find(int id) {
		Connection conn = DbUtil.getConnection();
		String sql = "select * from users where id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(conn);
		}
		return null;
	}
}
