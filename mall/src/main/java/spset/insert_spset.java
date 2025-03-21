package spset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.m_dbinfo;

public class insert_spset {
	Connection con = null;
	PreparedStatement ps = null;
	String sql = null;
	m_dbinfo db = new m_dbinfo();
	Integer result = null;
	ResultSet rs = null;
	
	
	public Integer insert_datas(spset_dto m) {
		try {
			this.con = this.db.getConnection();
			this.sql = "select count(*) from mallset";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			int counter = this.rs.getInt(1);
			this.rs.close();
			this.ps.close();
			if(counter>0) {
				this.sql = "update mallset set mname=?, admin_email=?, use_point=?, signup_point=?, signup_lv=?, cpname=?, cpno=?, ceoname=?, ceotel=?, ecommno=?, "
						+ "telcommno=?, bzipcode=?, baddr=?, secname=?, secmail=?, banknm=?, bankaccno=?, use_creditcard=?, use_mobile=?, use_gift=?, min_p_pay=?, "
						+ "max_p_pay=?, use_cash_rec=?, shipp_compnm=?, shipp_fee=?, des_deliv_date=?";
			}else {
				this.sql = "insert into mallset values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			}
			
			
			
			

			// DTO getter를 이용해 db에 저장
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, m.getMname());
			this.ps.setString(2, m.getAdmin_email());
			this.ps.setString(3, m.getUse_point());
			this.ps.setString(4, m.getSignup_point());
			this.ps.setString(5, m.getSignup_lv());
			this.ps.setString(6, m.getCpname());
			this.ps.setString(7, m.getCpno());
			this.ps.setString(8, m.getCeoname());
			this.ps.setString(9, m.getCeotel());
			this.ps.setString(10, m.getEcommno());
			this.ps.setString(11, m.getTelcommno());
			this.ps.setString(12, m.getBzipcode());
			this.ps.setString(13, m.getBaddr());
			this.ps.setString(14, m.getSecname());
			this.ps.setString(15, m.getSecmail());
			this.ps.setString(16, m.getBanknm());
			this.ps.setString(17, m.getBankaccno());
			this.ps.setString(18, m.getUse_creditcard());
			this.ps.setString(19, m.getUse_mobile());
			this.ps.setString(20, m.getUse_gift());
			this.ps.setString(21, m.getMin_p_pay());
			this.ps.setString(22, m.getMax_p_pay());
			this.ps.setString(23, m.getUse_cash_rec());
			this.ps.setString(24, m.getShipp_compnm());
			this.ps.setString(25, m.getShipp_fee());
			this.ps.setString(26, m.getDes_deliv_date());

//			System.out.println("SQL!!!!!!!!");
//			System.out.println(sql);
//			System.out.println("PS!!!!!!!!");
//			System.out.println(ps);
			this.result = this.ps.executeUpdate();

		} catch (Exception e) {
			this.result = null;
		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			} catch (Exception e) {
				this.result = null;
			}
		}
		return this.result;
	}
}
