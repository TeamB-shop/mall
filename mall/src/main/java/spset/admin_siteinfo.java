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
       

	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {

		RequestDispatcher rd = rq.getRequestDispatcher("./admin_siteinfo_ok.jsp");
		rd.forward(rq, rp);
	}

}
