package model;

import java.security.MessageDigest;

public class m_md5 {
	
	public String md5_code(String pw) {
		StringBuilder sb = new StringBuilder();
		String md5_data = "";  //����ڰ� �Է��� ���� md5�� ��ȣȭ ����
		
		try {
			MessageDigest md  = MessageDigest.getInstance("md5");
			md.update(pw.getBytes());
			byte md5byte[] = md.digest();
			
			//��ȣȭ �˰���
			for(byte alg : md5byte) {
				sb.append(String.format("%02x", alg));
			}
			md5_data = String.valueOf(sb);
			
		}catch (Exception e) {
			
		}
		
		return md5_data;
	}
}
