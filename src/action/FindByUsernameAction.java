package action;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Dao.UserDao;
import model.User;
import util.DbUtil;

public class FindByUsernameAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	UserDao userDao = null;
	DbUtil dbUtil = new DbUtil();
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		userDao = new UserDao();
		Connection con = null;
		String findByUsername = request.getParameter("findByUsername");
		try {
			con = dbUtil.getCon();
			User user = userDao.findByUsername(con, findByUsername);
			if(user!=null) {
				out.write(user.getUsername());
				out.flush();
				out.close();
				return null;
			}else {
				out.write("查无此人");
				out.flush();
				out.close();
				return null;   
			}
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
			out.write("查询出错");
			out.flush();
			out.close();
			return null;
		}finally {
			try{
				dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
}
