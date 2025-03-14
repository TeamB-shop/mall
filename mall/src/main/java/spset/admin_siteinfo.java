package spset;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.select_spset;


public class admin_siteinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	 // do로 연동되게 get으로 수정함
    protected void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
    	//세션 체크
    	 HttpSession session = rq.getSession(false); // 세션이 없으면 null 반환

    	    if (session.getAttribute("aname") == null) { 
    	        rp.sendRedirect("/mall/admin/index.do"); 
    	        return;
    	    }else {
    	    	System.out.println("세션있음!!" + session.getAttribute("aname"));
    	
    	
    	
    	spset_dto spdata = new select_spset().get_siteinfo();//dto 정보 가져오기용
    	rq.setAttribute("spdata", spdata);
    	
    	
        RequestDispatcher rd = rq.getRequestDispatcher("/admin/admin_siteinfo.jsp");
        rd.forward(rq, rp);
    	    }
    }

 
    

}
