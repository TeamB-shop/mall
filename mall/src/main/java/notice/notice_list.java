package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class notice_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		//dto는 나중에~
		
		RequestDispatcher rd = rq.getRequestDispatcher("/admin/notice_list.jsp");
		rd.forward(rq, rp);

		
	}

}
