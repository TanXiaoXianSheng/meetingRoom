package action;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import Dao.UserDao;
import model.User;
import net.sf.json.JSONObject;
import util.DbUtil;

public class PersonalProfileAction extends ActionSupport{

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
		String findResult = request.getParameter("findResult");
		
		try {
			con = dbUtil.getCon();
			User user = userDao.findAllByUsername(con, findResult);
			if(user!=null) {
				JSONObject resultJson=new JSONObject();
				resultJson.put("username", user.getUsername());
				resultJson.put("phone_number", user.getPhone_number());
				resultJson.put("sex", user.getSex());
				System.out.println(resultJson);
				out.print(resultJson);
				out.flush();
				out.close();
				return null;
			}
		}catch(Exception e1) {
			e1.printStackTrace();
			return null;
		}finally {
			try{
				dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return super.execute();
	}

	
}
