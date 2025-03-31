package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.file_delete;
import model.product_dto;
import model.product_query;

public class product_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String ckdel = request.getParameter("ckdel");
		String product_code[] = ckdel.split(",");
		String webapp_path = request.getServletContext().getRealPath("");
		
		product_query pquery = new product_query();
		int result = 0;
		try {
			for(int i=0; i<product_code.length; i++) {
				product_dto pdto = pquery.select_img_pass(product_code[i]);  
				result += pquery.delete_product("product_code", product_code[i]);  //product 테이블에서 선택한 product_code에 해당하는 데이터 삭제 
				new file_delete().f_delete(pdto, webapp_path);                     //웹서버에 저장된 이미지파일 삭제
			}
			if(result == product_code.length) {
				pw.write("<script>"
						+ "alert('선택상품 삭제가 완료 되었습니다.');"
						+ "location.href = './product_list.do';"
						+ "</script>");
			}
			else {
				pw.write("<script>"
						+ "alert('비정상적인 데이터가 확인되었습니다.');"
						+ "location.href = './product_list.do';"
						+ "</script>");				
			}
		}catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			pw.write("<script>"
					+ "alert('시스템 오류로 선택상품 삭제가 되지 않았습니다.');"
					+ "location.href = './product_list.do';"
					+ "</script>");					
		}
	}

}
