package product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.category_dto;
import model.category_query;
import model.random_number;

public class product_write extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String random_six_digit = new random_number().make_num(6);  //6자리 난수
		request.setAttribute("random_six_digit", random_six_digit);
		
		String msg = "";
		try {
			ArrayList<category_dto> category_all = new category_query().select_category_all();
			msg = "ok";
			request.setAttribute("msg", msg);
			request.setAttribute("category_all", category_all);
			
		}
		catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			msg = "시스템 오류로 카테고리가 로드되지 않았습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("category_all", null);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./product_write.jsp");
		rd.forward(request, response);	
	}
}
