package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class file_nename {

	public String rename(String filenm) {
		//속성
		int pos = filenm.lastIndexOf(".");
		String fnm = filenm.substring(pos);
		
		//날짜
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);  //년월일
		
		//4자리 랜덤값
		String no = new random_number().make_num(4); //0000~9999
		String makefile = today + no + fnm; //파일명 예시)202503301234.jpg
		
		return makefile;
	}
}
