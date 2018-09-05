package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/meeting";
	private String dbUserName = "root";
	private String dbPassword = "1226";
	private String jdbcName = "com.mysql.jdbc.Driver";
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		System.out.println(DataUtil.getData() + ":数据库连接成功");
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con != null){
			con.close();
		}
		System.out.println(DataUtil.getData() + ":数据库连接关闭");
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println(DataUtil.getData() + ":数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
