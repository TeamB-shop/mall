package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.admin_dto;
import model.data_query;
import model.m_md5;

public class join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		admin_dto dto = new admin_dto();
		dto.setAid(request.getParameter("aid"));
		dto.setApassword(new m_md5().md5_code(request.getParameter("apassword")));
		dto.setAname(request.getParameter("aname"));
		dto.setAemail(request.getParameter("aemail"));
		dto.setAtel1(request.getParameter("atel1"));
		dto.setAtel2(request.getParameter("atel2"));
		dto.setAdepartment(request.getParameter("adepartment"));
		dto.setAposiition(request.getParameter("aposiition"));

		
		//DTO���� insert Model ������
		Integer result = new data_query().insert_admin(dto);

		this.pw = response.getWriter();
		if(result > 0 ) {
			this.pw.write("<script>"
					+ "alert('������ ����� ����ó�� �Ǿ����ϴ�.');"
					+ "location.href='../admin/index.do';"
					+ "</script>");
		}else {
			this.pw.write("<script>"
					+ "alert('�ý��� �������� �����ڵ���� ���� �ʾҽ��ϴ�.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}

}
