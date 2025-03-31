package product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.file_nename;
import model.product_dto;
import model.product_query;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2, //2MB
		maxFileSize = 1024 * 1024 * 20, //최대용량 20MB
		maxRequestSize = 1024 * 1024 * 100  //여러개 파일 업로드시
)
public class product_write_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String upload_dir = "/product_image/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//첨부파일 다루기
		Part pfile_main = request.getPart("pfile_main");
		Part pfile_sub1 = request.getPart("pfile_sub1");
		Part pfile_sub2 = request.getPart("pfile_sub2");
		long filesize_main = pfile_main.getSize();
		long filesize_sub1 = pfile_sub1.getSize();
		long filesize_sub2 = pfile_sub2.getSize();
		String main_image_ori = null;  //사용자가 업로드한 대표 파일명 
		String main_image_new = null;  //대표 파일명 변경 
		String main_image_path = null; //서버에 저장된 대표 파일경로 및 파일명 
		String sub1_image_ori = null;  //사용자가 업로드한 서브1 파일명 
		String sub1_image_new = null;  //서브1 파일명 변경 
		String sub1_image_path = null; //서버에 저장된 서브1 파일경로 및 파일명 
		String sub2_image_ori = null;  //사용자가 업로드한 서브2 파일명 
		String sub2_image_new = null;  //서브2 파일명 변경 
		String sub2_image_path = null; //서버에 저장된 서브2 파일경로 및 파일명 
		String fileok = "";  //첨부파일 저장 오류 관련 

		if(filesize_main > 0 || filesize_sub1 > 0 || filesize_sub2 > 0) {
			
			//첨부파일 저장될 Web Directory 설정
			String upload_path = request.getServletContext().getRealPath(upload_dir);
			File upload_full_dir = new File(upload_path);
			
			// 디렉토리가 없으면 생성
			if (!upload_full_dir.exists()) {
				upload_full_dir.mkdirs();
			}
			
			try {
				if(filesize_main > 0) {
					main_image_ori = pfile_main.getSubmittedFileName();
					main_image_new = new file_nename().rename(main_image_ori);
					main_image_path  = upload_dir + main_image_new; //"/product_image/202503301234.jpg"
					pfile_main.write(upload_path+main_image_new);   //해당 디렉토에 저장
				}
				if(filesize_sub1 > 0) {
					sub1_image_ori = pfile_sub1.getSubmittedFileName();
					sub1_image_new = new file_nename().rename(sub1_image_ori);
					sub1_image_path  = upload_dir + sub1_image_new;
					pfile_sub1.write(upload_path+sub1_image_new); 				
				}
				if(filesize_sub2 > 0) {
					sub2_image_ori = pfile_sub2.getSubmittedFileName();
					sub2_image_new = new file_nename().rename(sub2_image_ori);
					sub2_image_path  = upload_dir + sub2_image_new;
					pfile_sub2.write(upload_path+sub2_image_new); 			
				}				
				
			}catch (Exception e) {
				System.out.println("파일저장 오류!!" + e.getMessage());
				fileok = "error";   //첨부파일 저장에 오류가 발생한 경우 
				pw.write("<script>"
						+ "alert('첨부파일 저장에 오류가 발생했습니다.');"
						+ "history.go(-1);"
						+ "</script>");						
			}
		}
		
		if(!fileok.equals("error")) {  //첨부파일이 없거나 첨부파일 서버저장이 정상인 경우 게시물 DB 저장 진행 
			
			//상품관리 입력 데이터를 product_dto에 임시저장
			product_dto pdto = new product_dto();
			pdto.setCategory_code(request.getParameter("category_code"));                    //카테고리 코드 
			pdto.setProduct_code(request.getParameter("product_code"));                      //상품코드  
			pdto.setProduct_name(request.getParameter("product_name"));                      //상품명 
			pdto.setProduct_description(request.getParameter("product_description"));        //상품 부가설명  
			pdto.setPrice(Integer.valueOf(request.getParameter("price")));                   //판매가격 
			pdto.setDiscount_rate(Integer.valueOf(request.getParameter("discount_rate")));   //할인율 
			pdto.setDiscount_price(Integer.valueOf(request.getParameter("discount_price"))); //할인가격 
			pdto.setStock(Integer.valueOf(request.getParameter("stock")));                   //상품재고 
			pdto.setSale_status(request.getParameter("sale_status"));                        //판매 유/무 
			pdto.setEarly_soldout(request.getParameter("early_soldout"));                    //조기품절 
			pdto.setMain_image_ori(main_image_ori);                                //사용자가 업로드한 대표 파일명 
			pdto.setMain_image_path(main_image_path);                                        //서버에 저장된 대표 파일경로 및 파일명 
			pdto.setSub1_image_ori(sub1_image_ori);                                //사용자가 업로드한 서브1 파일명 
			pdto.setSub1_image_path(sub1_image_path);                                        //서버에 저장된 서브1 파일경로 및 파일명 
			pdto.setSub2_image_ori(sub2_image_ori);                                //사용자가 업로드한 서브2 파일명 
			pdto.setSub2_image_path(sub2_image_path);                                        //서버에 저장된 서브2 파일경로 및 파일명 
			pdto.setDetail_description(request.getParameter("detail_description"));          //상품 상세설명 
	
			//product_dto에 저장된 상품관리 입력 데이터를 데이타베이스에 저장
			try {
				product_query pquery = new product_query();
				int result = pquery.insert_product(pdto); 
				if(result > 0) {
					pw.write("<script>"
							+ "alert('상품관리 등록이 완료 되었습니다.');"
							+ "location.href = './product_list.do';"
							+ "</script>");
				}
			}
			catch (Exception e) {
				e.printStackTrace();  // 예외 메시지 출력
				pw.write("<script>"
						+ "alert('시스템 오류로 상품관리가 등록되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>");					
			}
		}	
	}
}
