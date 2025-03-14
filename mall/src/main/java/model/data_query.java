package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class data_query {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	admin_dto admin = new admin_dto();
	m_dbinfo db = new m_dbinfo();
	String result = null;
	Integer res = 0; 
	
	public Integer insert_admin(admin_dto admin) {
		try {
			this.con = this.db.getConnection();
			this.sql = "insert into admin values ('0',?,?,?,?,?,?,?,?,'N',now())";
			this.ps = this.con.prepareStatement(this.sql);
			//DTO에서 getter 이용하여 DB에 저장
			this.ps.setString(1, admin.getAid());
			this.ps.setString(2, admin.getApassword());
			this.ps.setString(3, admin.getAname());
			this.ps.setString(4, admin.getAemail());
			this.ps.setString(5, admin.getAtel1());
			this.ps.setString(6, admin.getAtel2());
			this.ps.setString(7, admin.getAdepartment());
			this.ps.setString(8, admin.getAposiition());
			
			this.res = this.ps.executeUpdate();
	
		}catch (Exception e) {
			System.out.println(e);
			this.res = 0;
		}finally {
			try {
				this.ps.close();
				this.con.close();
			}catch (Exception e) {
				this.res = 0;
			}
		}
		
		return this.res;
	}	
	
	public String admin_login(admin_dto md) {
		
		try {
			this.con = this.db.getConnection();
			this.sql = "select aname, master from admin where aid=? and apassword=?";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, md.getAid());
			this.ps.setString(2, md.getApassword());
			this.rs = this.ps.executeQuery();
			if(this.rs.next() == true) { //정상적으로 아이디 및 패스워드가 맞을 경우
				this.result = "ok";
				this.admin.setAname(this.rs.getString("aname"));  //이름
				this.admin.setMaster(this.rs.getString("master"));  //master 여부('Y', 'N')
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
	
	public admin_dto get_admin_dto() {
		return this.admin;
	}
	
}
