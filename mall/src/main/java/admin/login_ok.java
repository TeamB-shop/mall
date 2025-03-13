package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.admin_dto;
import model.data_query;
import model.m_md5;
import model.master_dto;

public class login_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	admin_dto dto = new admin_dto();
	data_query dq = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		this.dto.setAid(request.getParameter("aid"));
		this.dto.setApassword(new m_md5().md5_code(request.getParameter("apassword")));
		
		this.dq = new data_query();  //data_quary Model 생성
		String result = dq.admin_login(this.dto); //Controller => Model로 DTO로 값을 전송
		
		admin_dto admin = dq.get_admin_dto(); //Model에서 DTO를 생성한 값을 Controller에 받는 역할
		String aid = admin.getAid();
		String master = admin.getMaster();

		if(result == "ok") {
			HttpSession session = request.getSession();
			session.setAttribute("aid", aid);
	
			if(master.equals("Y")) {
				this.pw.write("<script>"
						+ "alert('마스터님 로그인 하셨습니다..');"
						+ "location.href='./admin_list.do';"
						+ "</script>");
			}
			else {
				this.pw.write("<script>"
						+ "alert('관리자님 로그인 하셨습니다..');"
						+ "location.href='./admin_list.do';"
						+ "</script>");				
			}
		}
		else {
			this.pw.write("<script>"
					+ "alert('아이디 및 패스워드를 확인하세요.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
