package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Chat;

public class ChatDao {

	/**
	 * 聊天信息保存数据库
	 * @param con
	 * @param chat
	 * @return
	 * @throws SQLException
	 */
	public int saveChatContent(Connection con,Chat chat) throws SQLException {
		String sql = "INSERT INTO chattable(id,sendPerson,TIME,receivePerson,content,relativePath)VALUES(null,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, chat.getSendPerson());
		pstmt.setString(2, chat.getTime());
		pstmt.setString(3, chat.getReceivePerson());
		pstmt.setString(4, chat.getContent());
		pstmt.setString(5, chat.getRelativePath());
		return pstmt.executeUpdate();
	}
	

}
