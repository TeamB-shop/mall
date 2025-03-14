package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //로그아웃 기능
    protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
    	  HttpSession session = rq.getSession(false); // 세션이 존재하면 가져오고, 없으면 null 반환
          if (session != null) {
              session.invalidate(); // 세션 삭제
          }

          // 로그인 페이지로 리다이렉트
          rp.sendRedirect("/mall/admin/index.do");
      }
  }