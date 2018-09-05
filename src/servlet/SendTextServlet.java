package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ChatDao;
import model.Chat;
import util.DataUtil;
import util.DbUtil;

public class SendTextServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil = new DbUtil();
	ChatDao chatDao = new ChatDao();
	
	String comparePath = "a";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		System.out.println("交互开始");
		String sendPerson = (String)session.getAttribute("username");
		String time = DataUtil.getData();
		String receivePerson = request.getParameter("receivePerson");
		String content = request.getParameter("text");
		String relativePath = UploadServlet.relativePath;
		int flag = UploadServlet.flag;
		if(flag==0) {
			relativePath = null;
		}
		UploadServlet.setFlag(0);
		System.out.println("relativePath:" + relativePath);
		
		Connection con = null;
		try{
			con = dbUtil.getCon();
			Chat chat = new Chat(sendPerson,time,receivePerson,content,relativePath);
			int rs = chatDao.saveChatContent(con, chat);
			if(rs == 1) {
				out.write(chat.toString() + "\n");
				out.flush();
				out.close();
				System.out.println("交互结束");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			out.write("出现错误......" + "\n");
			out.flush();
			out.close();
		}finally {
			try{
				dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	
}
