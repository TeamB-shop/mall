package spset;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class admin_siteinfo_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PrintWriter pw = null;
       

	protected void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("utf-8");
		rp.setContentType("text/html;charset=utf-8");
		
		this.pw=rp.getWriter();
		
		String mname = rq.getParameter("mname");
		String admin_email = rq.getParameter("admin_email");
		String use_point = rq.getParameter("use_point");
		String signup_point = rq.getParameter("signup_point");
		String signup_lv = rq.getParameter("signup_lv");
		String cpname = rq.getParameter("cpname");
		String cpno = rq.getParameter("cpno");
		String ceoname = rq.getParameter("ceoname");
		String ceotel = rq.getParameter("ceotel");
		String ecommno = rq.getParameter("ecommno");
		String telcommno = rq.getParameter("telcommno");
		String bzipcode = rq.getParameter("bzipcode");
		String baddr = rq.getParameter("baddr");
		String secname = rq.getParameter("secname");
		String secmail = rq.getParameter("secmail");
		String banknm = rq.getParameter("banknm");
		String bankaccno = rq.getParameter("bankaccno");
		String use_creditcard = rq.getParameter("use_creditcard");
		String use_mobile = rq.getParameter("use_mobile");
		String use_gift = rq.getParameter("use_gift");
		String min_p_pay = rq.getParameter("min_p_pay");
		String max_p_pay = rq.getParameter("max_p_pay");
		String use_cash_rec = rq.getParameter("use_cash_rec");
		String shipp_compnm = rq.getParameter("shipp_compnm");
		String shipp_fee = rq.getParameter("shipp_fee");
		String des_deliv_date = rq.getParameter("des_deliv_date");
		
		//DTO로 쏩니다..
		spset_dto dto = new spset_dto();
		dto.setMname(mname);
		dto.setAdmin_email(admin_email);
		dto.setUse_point(use_point);
		dto.setSignup_point(signup_point);
		dto.setSignup_lv(signup_lv);
		dto.setCpname(cpname);
		dto.setCpno(cpno);
		dto.setCeoname(ceoname);
		dto.setCeotel(ceotel);
		dto.setEcommno(ecommno);
		dto.setTelcommno(telcommno);
		dto.setBzipcode(bzipcode);
		dto.setBaddr(baddr);
		dto.setSecname(secname);
		dto.setSecmail(secmail);
		dto.setBanknm(banknm);
		dto.setBankaccno(bankaccno);
		dto.setUse_creditcard(use_creditcard);
		dto.setUse_mobile(use_mobile);
		dto.setUse_gift(use_gift);
		dto.setMin_p_pay(min_p_pay);
		dto.setMax_p_pay(max_p_pay);
		dto.setUse_cash_rec(use_cash_rec);
		dto.setShipp_compnm(shipp_compnm);
		dto.setShipp_fee(shipp_fee);
		dto.setDes_deliv_date(des_deliv_date);
		
		Integer result = new insert_spset().insert_datas(dto);
		System.out.println("DB 저장 결과: " + result);
		
		if(result>0) {//성공
			pw.write("<script>alert('쇼핑몰 세팅완료'); history.go(-1);</script>");
		}else {//실패
			pw.write("<script>alert('쇼핑몰 세팅 실패...'); history.go(-1);</script>");
		}
		this.pw.close();
	}

}
