package javaDemo.dataDemo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataDemo {

	public static void main(String[] args) {
		try {
			// String time = "20120324";
			// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			// // SimpleDateFormat的parse(String time)方法将String转换为Date
			// Date date = simpleDateFormat.parse(time);
			// simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// // SimpleDateFormat的format(Date date)方法将Date转换为String
			// String formattedTime = simpleDateFormat.format(date);
			// System.out.println("---->将" + time + "解析为:" + formattedTime);
			String time = "20131227085009";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			// SimpleDateFormat的parse(String time)方法将String转换为Date
			Date date = simpleDateFormat.parse(time);
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// SimpleDateFormat的format(Date date)方法将Date转换为String
			String formattedTime = simpleDateFormat.format(date);
			System.out.println("---->将" + time + "解析为:" + formattedTime);
			System.out.println(date.getTime() + ":" + new Timestamp(date.getTime()));

		} catch (Exception e) {

		}
	}
}
