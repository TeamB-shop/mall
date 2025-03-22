package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.m_dbinfo;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize =  1024*1024*2,
		maxRequestSize = 1024*1024*5
		)
public class notice_write_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;
	m_dbinfo db = new m_dbinfo();
	PrintWriter pw = null;
	String sql = null;
	int res = 0;

	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("utf-8");
		rp.setContentType("text/html;charset=utf-8");
		
	   	 HttpSession session = rq.getSession(false); // 세션이 없으면 null 반환

 	    if (session.getAttribute("aname") == null) { 
 	        rp.sendRedirect("/mall/admin/index.do"); 
 	        return;
 	    }else {
 	    	pw=rp.getWriter();
 	    	String is_pin = rq.getParameter("ispin");
 			if(is_pin==null) {
 				is_pin= "N";
 			}
 			String title = rq.getParameter("title");
 			String writer = rq.getParameter("writer");
 			Part p= rq.getPart("mfile");
 			String fname = p.getSubmittedFileName();
 			String content = rq.getParameter("content");
 			
 			if(fname!="") {
 				String url = rq.getServletContext().getRealPath("/notice/");
 				p.write(url+fname);
 			}
 			
 	    	try {
				this.con = this.db.getConnection();
				this.sql = "insert into notice (midx,title, writer,views,is_pin,mfile,content,create_at )"
						+ "values('0',?,?,0,?,?,?,now())";
				con = db.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, title);
				ps.setString(2, writer);
				ps.setString(3, is_pin);
				ps.setString(4, fname);
				ps.setString(5, content);
				this.res = ps.executeUpdate();
				
				
			} catch (Exception e) {
				
			}finally {
				try {
					ps.close();
					con.close();
				}catch (Exception e) {
					
				}
			}
 	    	
 	    	if(res>0) {
 	    		 pw.write("<script>alert('공지사항 등록 완료'); location.href='./notice_list.do';</script>");
 	    	}else {
 	    		 pw.write("<script>alert('공지사항 등록 실패'); history.go(-1);</script>");
 	    	}
 			
 	    }


	}

}