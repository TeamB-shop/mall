package notice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import model.m_dbinfo;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,       // 1MB
    maxFileSize = 1024 * 1024 * 2,         // 2MB
    maxRequestSize = 1024 * 1024 * 5       // 5MB
)
public class notice_write_ok extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection con = null;
    PreparedStatement ps = null;
    m_dbinfo db = new m_dbinfo();
    String sql = null;
    int res = 0;

    protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
  

        rq.setCharacterEncoding("utf-8");
        rp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = rp.getWriter();

        HttpSession session = rq.getSession(false);
        if (session == null || session.getAttribute("aname") == null) {
            rp.sendRedirect("/mall/admin/index.do");
            return;
        }

        String is_pin = rq.getParameter("is_pin");
        if (is_pin == null) is_pin = "N";

        String title = rq.getParameter("title");
        String writer = rq.getParameter("writer");
        String content = rq.getParameter("content");

        Part filePart = rq.getPart("mfile");
        String fname = "";

        try {
            if (filePart != null && filePart.getSize() > 0) {
                fname = filePart.getSubmittedFileName();

                String savePath = rq.getServletContext().getRealPath("/notice");
                File uploadDir = new File(savePath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String fullPath = savePath + File.separator + fname;
                
                filePart.write(fullPath);
            } else {
               
            }

            // DB 입력 처리
            con = db.getConnection();
            sql = "insert into notice (midx, title, writer, views, is_pin, mfile, content, create_at) " +
                  "values ('0', ?, ?, 0, ?, ?, ?, now())";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, writer);
            ps.setString(3, is_pin);
            ps.setString(4, fname);
            ps.setString(5, content);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		ps.close();
        		con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }

        if (res > 0) {
            pw.write("<script>alert('공지사항 등록 완료'); location.href='./notice_list.do';</script>");
        } else {
            pw.write("<script>alert('공지사항 등록 실패'); history.go(-1);</script>");
        }
    }
}
