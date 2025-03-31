package product;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.category_query;

public class category_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ctn_per_page = 5;  //페이지당 출력갯수

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageno = request.getParameter("pageno");
		if(pageno == null) { //최초 접속시 1페이지 화면 출력 
			pageno = "1";
		}
		
		String search_key = request.getParameter("search_key");
		String search_word = request.getParameter("search_word");
		
		Map<String, Object> result = null;
		try {
			if(search_key == null || search_word == null) {
				result = new category_query().select_category_page(Integer.parseInt(pageno), this.ctn_per_page);	
			}
			else {
				result = new category_query().select_category_search(search_key, search_word, this.ctn_per_page);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			result = null;
		}
		request.setAttribute("ctn_per_page", ctn_per_page);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("./category_list.jsp");
		rd.forward(request, response);		
	}
}
