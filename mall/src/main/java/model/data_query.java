package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class data_query {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = null;
	admin_dto admin = null;
	m_dbinfo db = new m_dbinfo();
	String result = null;
	Integer res = 0; 
	ArrayList<admin_dto> admins= null;
	
	public Integer insert_admin(admin_dto admin) {
		try {
			this.admin = new admin_dto();
			this.con = this.db.getConnection();
			this.sql = "insert into admin values ('0',?,?,?,?,?,?,?,?,'N','N',now())";
			this.ps = this.con.prepareStatement(this.sql);
			//DTO에서 getter 이용하여 DB에 저장
			this.ps.setString(1, admin.getAid());
			this.ps.setString(2, admin.getApassword());
			this.ps.setString(3, admin.getAname());
			this.ps.setString(4, admin.getAemail());
			this.ps.setString(5, admin.getAtel1());
			this.ps.setString(6, admin.getAtel2());
			this.ps.setString(7, admin.getAdepartment());
			this.ps.setString(8, admin.getAposition());
			
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
			this.admin = new admin_dto();
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
	
	public ArrayList<admin_dto> select_admin() {

		try {
			this.admins = new ArrayList<admin_dto>();
			this.con = this.db.getConnection();
			this.sql = "select * from admin";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();
			while(this.rs.next() == true) { 
				this.admin = new admin_dto();
				this.admin.setAidx(this.rs.getString("aidx"));  
				this.admin.setAid(this.rs.getString("aid"));  
				this.admin.setAname(this.rs.getString("aname"));  
				this.admin.setAtel1(this.rs.getString("atel1"));  
				this.admin.setAtel2(this.rs.getString("atel2"));  
				this.admin.setAemail(this.rs.getString("aemail"));  
				this.admin.setAdepartment(this.rs.getString("adepartment"));  
				this.admin.setAposition(this.rs.getString("aposition"));  
				this.admin.setApproval(this.rs.getString("approval"));  
				this.admin.setMaster(this.rs.getString("master"));  
				this.admin.setAdate(this.rs.getString("adate"));  
				
				this.admins.add(this.admin);
			}
			
		}catch (Exception e) {
			System.out.println(e);
			this.admins = null;
		}finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch (Exception e) {
				System.out.println("Database Error!!");
			}
		}
		
		return this.admins;
	}
	
}
