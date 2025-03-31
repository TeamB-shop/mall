package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class category_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String ckdel = request.getParameter("ckdel");
		String category_code[] = ckdel.split(",");
		category_query cquery = new category_query();
		int result = 0;
		try {
			for(int i=0; i<category_code.length; i++) {
				result += cquery.delete_category(category_code[i]); 
			}
			if(result == category_code.length) {
				pw.write("<script>"
						+ "alert('카테고리 삭제가 완료 되었습니다.');"
						+ "location.href = './category_list.do';"
						+ "</script>");
			}
			else {
				pw.write("<script>"
						+ "alert('비정상적인 데이터가 확인되었습니다.');"
						+ "location.href = './category_list.do';"
						+ "</script>");				
			}
		}catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			pw.write("<script>"
					+ "alert('시스템 오류로 카테고리 삭제가 되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");					
		}
	}
}
