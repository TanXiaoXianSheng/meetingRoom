package action;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Dao.FriendDao;
import model.Friend;
import util.DataUtil;
import util.DbUtil;

public class AddFriendAction extends ActionSupport{

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
		String friendUsername = request.getParameter("friendUsername");
		String user = (String) session.getAttribute("username");
		String time = DataUtil.getData();
		
		try {
			con = dbUtil.getCon();
			Friend friend = new Friend(friendUsername,user,time);
			int rs = friendDao.addFriend(con,friend);
			if(rs == 1) {
				out.print("添加成功");
				out.flush();
				out.close();
			}else {
				out.print("添加失败");
				out.flush();
				out.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			out.write("添加失败");
			out.flush();
			out.close();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	
}
