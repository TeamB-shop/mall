package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.random_number;

public class productcode_check extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String msg = "";
		String product_code = request.getParameter("product_code");

		try {
			String count = new product_query().check_duplicate(product_code);
			if(count.equals("0")) {				
				msg = "ok";
			}
			else {
				String random_six_digit = new random_number().make_num(6);  //6자리 난수
				msg = random_six_digit;
			}
		}
		catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			msg = "error";
		}
		
		pw.write(msg);
		pw.close();
	}
}
