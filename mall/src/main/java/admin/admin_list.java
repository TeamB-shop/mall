package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.admin_dto;
import model.data_query;

public class admin_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<admin_dto> admins= null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		data_query dquary = new data_query();
		this.admins = dquary.select_admin();
		
		request.setAttribute("admins", this.admins);
		
		RequestDispatcher rd = request.getRequestDispatcher("./admin_list.jsp");
		rd.forward(request, response);
	}

}
