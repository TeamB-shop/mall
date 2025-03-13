package adlist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data_query;
import model.m_md5;
import model.master_dto;

public class masterloginok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	master_dto md = new master_dto();
	data_query dq = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		this.md.setMid(request.getParameter("mid"));
		this.md.setMpass(new m_md5().md5_code(request.getParameter("mpass")));
		
		this.dq = new data_query();  //data_quary Model 생성
		String result = dq.master_login(this.md); //Controller => Model로 DTO로 값을 전송
		
		master_dto md2 = dq.get_master_dto(); //Model에서 DTO를 생성한 값을 Controller에 받는 역할
		
		if(result == "ok") {
			HttpSession session = request.getSession();
			session.setAttribute("mid", md2.getMid());
						
			this.pw.write("<script>"
					+ "alert('Master님 로그인 하셨습니다..');"
					+ "location.href='../.do';"
					+ "</script>");
		}
		else {
			this.pw.write("<script>"
					+ "alert('아이디 및 패스워드를 확인하세요.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
