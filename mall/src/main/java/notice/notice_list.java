package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.m_dbinfo;


public class notice_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	m_dbinfo db = null;
 		Connection con = null;
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		ArrayList<ArrayList<String>> noticelist = null;
 		

	
 
	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		
		
	  	 HttpSession session = rq.getSession(false); // 세션이 없으면 null 반환

 	    if (session.getAttribute("aname") == null) { 
 	        rp.sendRedirect("/mall/admin/index.do"); 
 	        return;
 	    }else {
 	    	try {
				this.db = new m_dbinfo();
				con = db.getConnection();
				String sql = "select title, writer, create_at, views from notice order by midx desc";
				this.ps = con.prepareStatement(sql);
				this.rs = this.ps.executeQuery();
				while(rs.next()) {
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
 	
 	    	
		RequestDispatcher rd = rq.getRequestDispatcher("/admin/notice_list.jsp");
		rd.forward(rq, rp);
 	    }
		
	}

}
