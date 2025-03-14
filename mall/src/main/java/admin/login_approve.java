package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data_query;

public class login_approve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String aid = request.getParameter("aid");
		String approval = request.getParameter("approval");
		
		data_query dquery = new data_query();
		Integer result = dquery.change_approval(aid, approval);
		
		if(result > 0) {
			pw.write(approval);
		}
		else {
			pw.write("no");
		}
	}

}
