package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.m_dbinfo;


public class notice_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	m_dbinfo db = new m_dbinfo();

	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("utf-8");
		rp.setContentType("text/html; charset=utf-8");

		String midx = rq.getParameter("midx");
		if (midx == null || midx.equals("")) {
			rp.sendRedirect("./notice_list.do");
		}
		else {
			
			try {
				this.con = this.db.getConnection();
				

				// 조회수 1 증가
				String updateSql = "update notice set views = views + 1 where midx = ?";
				ps = con.prepareStatement(updateSql);
				ps.setString(1, midx);
				ps.executeUpdate();
				ps.close();

				// 공지 데이터 조회
				String sql = "select midx, title, writer, create_at, views, content, mfile FROM notice where midx = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, midx);
				rs = ps.executeQuery();

				if (rs.next()) {
					rq.setAttribute("midx", rs.getString("midx"));
					rq.setAttribute("title", rs.getString("title"));
					rq.setAttribute("writer", rs.getString("writer"));
					rq.setAttribute("create_at", rs.getString("create_at"));
					rq.setAttribute("views", rs.getString("views"));
					rq.setAttribute("content", rs.getString("content"));
					rq.setAttribute("mfile", rs.getString("mfile"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
					con.close();
				} catch (Exception e) {
				}
			}

			RequestDispatcher rd = rq.getRequestDispatcher("/admin/notice_view.jsp");
			rd.forward(rq, rp);
		}
	}
}
