package action;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import Dao.UserDao;
import util.DbUtil;

public class RegisterAction implements Action{
	
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	
	private String username;
	private String password;
	private String confirm_password;
	private int phone_number;
	private String sex;
	
	
	public RegisterAction() {
		super();
	}

	public RegisterAction(String username, String password,  int phone_number, String sex) {
		super();
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.sex = sex;
	}
	
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
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String execute() throws Exception {
		String result = null;
		Connection con = null;
		try {
			con = dbUtil.getCon();
			RegisterAction register = new RegisterAction(username,password,phone_number,sex);
			int rs;
			try {
				rs = userDao.register(con, register);
			}catch(Exception e) {
				return ERROR;
				
			}
			
			if(rs == 1) {
				result = SUCCESS;
			}
			else {
				result = ERROR;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	
}
