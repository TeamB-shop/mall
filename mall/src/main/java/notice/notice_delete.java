package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_dbinfo;


//public class notice_delete extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
//        rp.setContentType("text/html; charset=utf-8");
//        
//		String midx = rq.getParameter("midx");
//	    Connection con = null;
//	    PreparedStatement ps = null;
//	    m_dbinfo db = new m_dbinfo();
//	    int result = 0;
//
//        try {
//            con = db.getConnection();
//            String sql = "delete from notice where midx = ?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, midx);
//            result = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { if (ps != null) ps.close(); } catch (Exception e) {}
//            try { if (con != null) con.close(); } catch (Exception e) {}
//        }
//
//        PrintWriter pw = rp.getWriter();
//        if (result > 0) {
//            pw.write("<script>alert('삭제 완료'); location.href='./notice_list.do';</script>");
//        } else {
//            pw.write("<script>alert('삭제 실패'); history.go(-1);</script>");
//        }
//    }
//}

public class notice_delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
        rp.setContentType("text/html; charset=utf-8");

        // JSP에서 전달한 체크박스 값은 "ids"라는 이름으로 여러 값이 전달됩니다.
        String[] ids = rq.getParameterValues("ids");
        Connection con = null;
        PreparedStatement ps = null;
        m_dbinfo db = new m_dbinfo();
        int totalDeleted = 0;

        try {
            con = db.getConnection();
            String sql = "delete from notice where midx = ?";
            ps = con.prepareStatement(sql);
            
            if (ids != null) {
                for (String id : ids) {
                    ps.setString(1, id);
                    totalDeleted += ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        PrintWriter pw = rp.getWriter();
        if (totalDeleted > 0) {
            pw.write("<script>alert('삭제 완료'); location.href='./notice_list.do';</script>");
        } else {
            pw.write("<script>alert('삭제 실패'); history.go(-1);</script>");
        }
    }
    

}
