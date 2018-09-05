package iFLYTEK;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import net.sf.json.JSONObject;

public class TestWebIat {
	// 听写webapi接口地址
	private static final String WEBIAT_URL = "http://api.xfyun.cn/v1/service/v1/iat";
	// 测试应用ID
	private static final String TEST_APPID = "5ae06185";
	// 测试接口密钥
	private static final String TEST_API_KEY = "7e5a98dfdbe5a427d032d2601f1dbe94";
	
	private static String audioFilePath;
	
	public static String getFile(String path) {
		audioFilePath = path;
		return audioFilePath;
	}
	
	/**
	 * 组装http请求头
	 * 
	 */
	private static Map<String, String> constructHeader(String aue, String engineType) throws UnsupportedEncodingException, ParseException {
		// 系统当前时间戳
		String X_CurTime = System.currentTimeMillis() / 1000L + "";
		// 业务参数
		String param = "{\"aue\":\""+aue+"\""+",\"engine_type\":\"" + engineType + "\"}";
		String X_Param = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
		// 接口密钥
		String apiKey = TEST_API_KEY;
		// 讯飞开放平台应用ID
		String X_Appid = TEST_APPID;
		// 生成令牌
		String X_CheckSum = DigestUtils.md5Hex(apiKey + X_CurTime + X_Param);

		// 组装请求头
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		header.put("X-Param", X_Param);
		header.put("X-CurTime", X_CurTime);
		header.put("X-CheckSum", X_CheckSum);
		header.put("X-Appid", X_Appid);
		return header;
	}
	
	/**
	 * 把mian方法包装成一个方法，供外部调用
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String speechRecongnition(String path) throws IOException,ParseException{
		Map<String, String> header = constructHeader("raw", "sms16k");
		byte[] audioByteArray = FileUtil.read2ByteArray(getFile(path));
		String audioBase64 = new String(Base64.encodeBase64(audioByteArray), "UTF-8");
		String bodyParam = "audio=" + audioBase64;
		String result = HttpUtil.doPost(WEBIAT_URL, header, bodyParam);
		return result;
	}
	
	public static String speechRecongnition() throws IOException,ParseException{
		Map<String, String> header = constructHeader("raw", "sms16k");
		byte[] audioByteArray = FileUtil.read2ByteArray(getFile("F:\\大三下\\JavaEE框架\\期末项目\\project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\voice\\upload\\test.wav"));
		
		String audioBase64 = new String(Base64.encodeBase64(audioByteArray), "UTF-8");
		String bodyParam = "audio=" + audioBase64;
		String result = HttpUtil.doPost(WEBIAT_URL, header, bodyParam);
		return result;
	}

	/**
	 * 测试，测试语音识别是否接通
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException,ParseException{
		String result = TestWebIat.speechRecongnition("resource/test3.wav");
		JSONObject json = JSONObject.fromObject(result);  
        System.out.println(json.toString());
	}
}
