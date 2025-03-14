package spset;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class admin_siteinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	 // do로 연동되게 get으로 수정함
    protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
        RequestDispatcher rd = rq.getRequestDispatcher("/admin/admin_siteinfo.jsp");
        rd.forward(rq, rp);
    }

 
    

}
