package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_dbinfo;

public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	m_dbinfo db = new m_dbinfo();
	String msg = ""; //����Ʈ�ص忡 ���� ����� ����
	String sql = "";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		String aid = request.getParameter("aid");

		try {
			if(aid.equals("") || aid.equals(null)) {
				this.msg = "error";
			}
			else {
				this.con = db.getConnection();
				this.sql = "select count(*) as ctn from admin where aid=?";
				this.ps = this.con.prepareStatement(this.sql);
				this.ps.setString(1, aid);
				this.rs = this.ps.executeQuery();
				
				if(this.rs.next()) {
					if(this.rs.getString("ctn").equals("0")) {
						this.msg = "ok";
					}
					else {
						this.msg = "no";
					}
				}
			}
			this.pw.write(this.msg); //ajax�� ����� �������� PrintWriter�� �̿�
				
		}
		catch (Exception e) {
			System.out.println(e);
			this.msg = "CODE Error : 844"; //������ �����ڵ� <- DB Connection ����
			this.pw.write(this.msg);
		}
		finally {
			this.pw.close();
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			}catch (Exception e) {
				
			}
		}
	}

}
