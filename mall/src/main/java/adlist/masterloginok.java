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
		
		this.dq = new data_query();  //data_quary Model ����
		String result = dq.master_login(this.md); //Controller => Model�� DTO�� ���� ����
		
		master_dto md2 = dq.get_master_dto(); //Model���� DTO�� ������ ���� Controller�� �޴� ����
		
		if(result == "ok") {
			HttpSession session = request.getSession();
			session.setAttribute("mid", md2.getMid());
						
			this.pw.write("<script>"
					+ "alert('Master�� �α��� �ϼ̽��ϴ�..');"
					+ "location.href='../.do';"
					+ "</script>");
		}
		else {
			this.pw.write("<script>"
					+ "alert('���̵� �� �н����带 Ȯ���ϼ���.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
