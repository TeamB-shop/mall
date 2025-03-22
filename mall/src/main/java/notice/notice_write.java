package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class notice_write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		//세션 체크
   	 HttpSession session = rq.getSession(false); // 세션이 없으면 null 반환

   	    if (session.getAttribute("aname") == null) { 
   	        rp.sendRedirect("/mall/admin/index.do"); 
   	        return;
   	    }else {
   	    	
   	    	RequestDispatcher rd = rq.getRequestDispatcher("/admin/notice_write.jsp");
   	    rd.forward(rq, rp);
   	    }
	}

}
