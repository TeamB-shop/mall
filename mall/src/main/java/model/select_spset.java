package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import spset.spset_dto;

public class select_spset {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	m_dbinfo db = new m_dbinfo();
	
	public spset_dto get_siteinfo() {
		spset_dto dto = new spset_dto();
		try {
            this.con = db.getConnection();
            this.ps = this.con.prepareStatement("select * from mallset;"); 
            this.rs = this.ps.executeQuery();

            if (this.rs.next()) {
                dto.setMname(this.rs.getString("mname"));
                dto.setAdmin_email(this.rs.getString("admin_email"));
                dto.setUse_point(this.rs.getString("use_point"));
                dto.setSignup_point(this.rs.getString("signup_point"));
                dto.setSignup_lv(this.rs.getString("signup_lv"));
                dto.setCpname(this.rs.getString("cpname"));
                dto.setCpno(this.rs.getString("cpno"));
                dto.setCeoname(this.rs.getString("ceoname"));
                dto.setCeotel(this.rs.getString("ceotel"));
                dto.setEcommno(this.rs.getString("ecommno"));
                dto.setTelcommno(this.rs.getString("telcommno"));
                dto.setBzipcode(this.rs.getString("bzipcode"));
                dto.setBaddr(this.rs.getString("baddr"));
                dto.setSecname(this.rs.getString("secname"));
                dto.setSecmail(this.rs.getString("secmail"));
                dto.setBanknm(this.rs.getString("banknm"));
                dto.setBankaccno(this.rs.getString("bankaccno"));
                dto.setUse_creditcard(this.rs.getString("use_creditcard"));
                dto.setUse_mobile(this.rs.getString("use_mobile"));
                dto.setUse_gift(this.rs.getString("use_gift"));
                dto.setMin_p_pay(this.rs.getString("min_p_pay"));
                dto.setMax_p_pay(this.rs.getString("max_p_pay"));
                dto.setUse_cash_rec(this.rs.getString("use_cash_rec"));
                dto.setShipp_compnm(this.rs.getString("shipp_compnm"));
                dto.setShipp_fee(this.rs.getString("shipp_fee"));
                dto.setDes_deliv_date(this.rs.getString("des_deliv_date"));
            }

        } catch (Exception e) {
        } finally {
            try {
                this.rs.close();
                this.ps.close();
                this.con.close();
            } catch (Exception e) {
            }
        }
        return dto;
    }
}