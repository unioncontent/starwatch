package org.union.util;

import java.text.SimpleDateFormat;

public class DateUtil {

	
	public void StringToDate(String date) {
		
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		System.out.println(afterFormat.format("20171018"));
	}

}
