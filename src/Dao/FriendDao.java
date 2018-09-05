package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Friend;

public class FriendDao {

	/**
	 * 给用户添加好友
	 * @param con
	 * @param friend
	 * @return
	 * @throws SQLException
	 */
	public int addFriend(Connection con,Friend friend) throws SQLException {
		String sql = "insert into friends(id,friend,user,time)values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, friend.getFriend());
		pstmt.setString(2, friend.getUser());
		pstmt.setString(3, friend.getTime());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查找用户好友
	 * @param con
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public ResultSet findFriends(Connection con,String username) throws SQLException {
		String sql = "select * from friends where user = " + username;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
