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
		
		this.dq = new data_query();  //data_quary Model ����
		String result = dq.admin_login(this.dto); //Controller => Model�� DTO�� ���� ����
		
		admin_dto admin = dq.get_admin_dto(); //Model���� DTO�� ������ ���� Controller�� �޴� ����
		String aid = admin.getAid();
		String master = admin.getMaster();

		if(result == "ok") {
			HttpSession session = request.getSession();
			session.setAttribute("aid", aid);
	
			if(master.equals("Y")) {
				this.pw.write("<script>"
						+ "alert('�����ʹ� �α��� �ϼ̽��ϴ�..');"
						+ "location.href='./admin_list.do';"
						+ "</script>");
			}
			else {
				this.pw.write("<script>"
						+ "alert('�����ڴ� �α��� �ϼ̽��ϴ�..');"
						+ "location.href='./admin_list.do';"
						+ "</script>");				
			}
		}
		else {
			this.pw.write("<script>"
					+ "alert('���̵� �� �н����带 Ȯ���ϼ���.');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
