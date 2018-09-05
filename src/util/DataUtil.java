package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {
	
	public static String getData() {
		Date date = new Date();
		//System.out.println("data:" + date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(format.format(date));
		String data2 = format.format(date);
		//System.out.println(data2);
		return data2;
	}
	
	/**
	 * 文件名时间
	 * @param i
	 * @return
	 */
	public static String getData(int i) {
		Date date = new Date();
		//System.out.println("data:" + date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd HH-mm-ss");
		//System.out.println(format.format(date));
		String data2 = format.format(date);
		//System.out.println(data2);
		return data2;
	}
	
	public static void getCalendar() {
		Calendar calendar = Calendar.getInstance();
		System.out.println("calendar:" + calendar.get(Calendar.YEAR));
	}

	public static void main(String[] args) {
		getData();
		getCalendar();
	}

}
