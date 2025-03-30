package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class category_write_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//카테고리 입력 데이터를 category_dto에 임시저장
		category_dto cdto = new category_dto();
		cdto.setCategory_code(request.getParameter("category_code"));  //카테고리 코드 
		cdto.setCategory_name(request.getParameter("category_name"));  //카테고리명 

		//category_dto에 저장된 카테고리 입력 데이터를 데이타베이스에 저장
		category_query cquery = new category_query();
		try {
			int result = cquery.insert_category(cdto); 
			if(result > 0) {
				pw.write("<script>"
						+ "alert('카테고리 등록이 완료 되었습니다.');"
						+ "location.href = './category_list.do';"
						+ "</script>");
			}
			
		}catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			pw.write("<script>"
					+ "alert('카테고리 코드 또는 카테고리명 중복으로 등록이 되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");							
		}catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			pw.write("<script>"
					+ "alert('오류로 카테고리 등록이 되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");					
		}
	}
}
