package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import iFLYTEK.TestWebIat;
import net.sf.json.JSONObject;
import util.DataUtil;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//�ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "upload";

	static String relativePath = null;
	static int flag = 0;
	
	public static String getRelativePath() {
		return relativePath;
	}
	
	public static void setFlag(int flag) {
		UploadServlet.flag = flag;
	}



	//�ϴ�����
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

	//�ϴ����ݼ������ļ�
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String fileName = "error";
		//����ļ��Ƿ�Ϊ��ý���ϴ�
		if(!ServletFileUpload.isMultipartContent(request)) {
			//���������ֹͣ
			PrintWriter writer = response.getWriter();
			writer.println("Error:���������enctype=multipart/form-data");
			writer.flush();
			return;
		}
		//�����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ڴ��ٽ�ֵ�������������ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//������ʱ�洢Ŀ¼
		//System.out.println("System.getProperty(\"java.io.tmpdir\")" + System.getProperty("java.io.tmpdir"));
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		//�����ļ�����ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//�����������ֵ
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//���Ĵ���
		upload.setHeaderEncoding("utf-8");
		
		//������ʱ·�����洢�ϴ����ļ�
		//���·������ڵ�ǰӦ�õ�Ŀ¼
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		//System.out.println("uploadPath:" + uploadPath);
		
		//���Ŀ¼�������򴴽�
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		try {
			//���������������ȡ�ļ�
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			
			if(formItems != null && formItems.size() > 0) {
				//����������
				for(FileItem item:formItems) {
					if(!item.isFormField()) {
						//String fileName = "test.wav";
						fileName = "[" + DataUtil.getData(1) + "]" + username + ".wav"; 
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						//�ڿ���̨����ļ����ϴ�·��
						//System.out.println(filePath);
						//�����ļ���Ӳ��
						item.write(storeFile);
						request.setAttribute("message", "�ļ��ϴ��ɹ�");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�ļ��ϴ�ʧ��");
		}
		//ʶ��
		//System.out.println("request.getContextPath()" + request.getContextPath());
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/upload/" + fileName); 
		relativePath = "upload/" + fileName;
		flag = 1;
		//path = "/upload/" + fileName;
		String result = null;
		try {
			result = TestWebIat.speechRecongnition(path);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//String转json
		JSONObject json = JSONObject.fromObject(result);  
        System.out.println("json.toString():" + json.toString()); 
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
		out.close();
		//end
	}

	
}
