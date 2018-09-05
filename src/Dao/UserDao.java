package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import action.LoginAction;
import action.RegisterAction;
import model.User;

public class UserDao {
	
	/**
	 * 登录查询֤
	 * @param con
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public LoginAction login(Connection con,String username,String password) throws SQLException {
		LoginAction loginAction = null;
		String sql = "select * from users where username=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,username);
		pstmt.setString(2,password);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			loginAction = new LoginAction();
			loginAction.setUsername(rs.getString("username"));
			loginAction.setPassword(rs.getString("password"));
		}
		return loginAction;
	}
	
	/**
	 * 注册插入数据
	 * @param con
	 * @param register
	 * @return
	 * @throws SQLException 
	 */
	public int register(Connection con,RegisterAction register) throws SQLException {
		String sql = "insert into users value(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, register.getUsername());
		pstmt.setString(2, register.getPassword());
		pstmt.setInt(3, register.getPhone_number());
		pstmt.setString(4, register.getSex());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 根据用户名查找用户，只查找密码，精确查找
	 * @param con
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User findByUsername(Connection con,String username) throws SQLException {
		User user = null;
		String sql = "select username from users where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			user = new User();
			user.setUsername(rs.getString("username"));
		}
		return user;
	}
	
	/**
	 * 根据用户名查找用户，查找所有信息
	 * @param con
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User findAllByUsername(Connection con,String username) throws SQLException {
		User user = null;
		String sql = "select * from users where username=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			user = new User();
			user.setUsername(rs.getString("username"));
			user.setPhone_number(rs.getInt("phone_number"));
			user.setSex(rs.getString("sex"));
		}
		return user;
	}
}
