package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Dao.FriendDao;
import util.DbUtil;

public class ReadFriendsList extends ActionSupport{

	private static final long serialVersionUID = 1L;

	FriendDao friendDao = null;
	DbUtil dbUtil = new DbUtil();
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		friendDao = new FriendDao();
		Connection con = null;
		String username = (String) session.getAttribute("username");
		try {
			con=dbUtil.getCon();
			System.out.println("con:" + con);
			ResultSet rs = friendDao.findFriends(con, username);
			session.setAttribute("friendsResultSet", rs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
}
