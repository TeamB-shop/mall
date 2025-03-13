package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class data_query {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	master_dto mmd = new master_dto();
	m_dbinfo db = new m_dbinfo();
	String result = null;
	
	public String master_login(master_dto md) {
		
		try {
			this.con = this.db.getConnection();
			this.sql = "select mid from master where mid=? and mpass=?";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, md.getMid());
			this.ps.setString(2, md.getMpass());
			this.rs = this.ps.executeQuery();
			if(this.rs.next() == true) { //정상적으로 아이디 및 패스워드가 맞을 경우
				this.result = "ok";
				this.mmd.setMid(this.rs.getString("mid"));  //마스터 아이디
			}
			
		}catch (Exception e) {
			System.out.println(e);
			this.result = null;
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch (Exception e) {
				System.out.println("Database Error!!");
			}
		}
		
		return this.result;
	}
	
	public master_dto get_master_dto() {
		return this.mmd;
	}
	
}
