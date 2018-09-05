package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Dao.FriendDao;
import Dao.UserDao;
import util.DataUtil;
import util.DbUtil;

public class LoginAction extends ActionSupport{
	
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	FriendDao friendDao = new FriendDao();

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		String result = null;
		Connection con1 = null;
		Connection con2 = null;
		try {
			con1 = dbUtil.getCon();
			LoginAction loginAction = userDao.login(con1, getUsername(), getPassword());
			if(loginAction != null) {
				ActionContext cxt = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) cxt.get(ServletActionContext.HTTP_REQUEST);
				HttpServletResponse response = (HttpServletResponse) cxt.get(ServletActionContext.HTTP_RESPONSE);
				HttpSession session =request.getSession();
				ServletContext application = session.getServletContext();
				//记住密码，设置cookie
				String remember = request.getParameter("remember");
				if("remember-me".equals(remember)) {
					Cookie userNameAndPassword = new Cookie("userNameAndPassword",username + "-" + password);
					userNameAndPassword.setMaxAge(10*60);//10分钟
					response.addCookie(userNameAndPassword);
					//cookie设置结束
				}
				//获取在线用户，加入到application中
				session.setAttribute("username", username);
				List onlineUserSet = (List) application.getAttribute("onlineUserSet");
				if(onlineUserSet == null) {
					onlineUserSet = new ArrayList();
					application.setAttribute("onlineUserSet", onlineUserSet);
				}
				Boolean flag = true;
				for(int i = 0;i < onlineUserSet.size();i ++) {
					if(onlineUserSet.get(i).equals(username)) {
						flag = false;
					}
				}
				if(flag) {
					onlineUserSet.add(username);
					
				}
				else {
					result = SUCCESS;
					System.out.println(DataUtil.getData() + ":用户已存在");
				}
				//在线用户加入结束
				
				
				result = SUCCESS;
			}
			else {
				result = ERROR;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return result;
		
	}
	@Override
	public void validate() {
		if(username == null || username.trim().equals("") || username.length() ==0) {
			addFieldError("username", "请重新输入用户名");
		}
		if(password == null || password.trim().equals("") || password.length() ==0) {
			addFieldError("password", "请重新输入密码");
		}
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session =request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode");
		String checkcode = request.getParameter("code");
		if(checkcode.equals(verificationCode)==false){
			addFieldError("code", "请重新输入验证码");
		}
	}

	
}