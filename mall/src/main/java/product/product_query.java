package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.m_dbinfo;

public class product_query {
	
	private m_dbinfo db = new m_dbinfo(); 
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public String check_duplicate(String product_code) throws Exception {
    	
    	this.con = db.getConnection();
    	String sql = "select count(*) as ctn from product where product_code=?";
    	this.ps = con.prepareStatement(sql);
    	this.ps.setString(1, product_code);
    	this.rs = this.ps.executeQuery();
    	this.rs.next();
    	String count = this.rs.getString("ctn");
    	
        try {
        	if (this.rs != null) this.rs.close();
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }	
    	
    	return count;
    }
    
    public int insert_product(product_dto pdto) throws Exception{
    	
    	int result = 0;
    	
		this.con = db.getConnection();
		String sql = "insert into product values ('0',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
		this.ps = con.prepareStatement(sql);
		this.ps.setString(1, pdto.getCategory_code());
		this.ps.setString(2, pdto.getProduct_code());
		this.ps.setString(3, pdto.getProduct_name());
		this.ps.setString(4, pdto.getProduct_description());
		this.ps.setInt(5, pdto.getPrice());
		this.ps.setInt(6, pdto.getDiscount_rate());
		this.ps.setInt(7, pdto.getDiscount_price());
		this.ps.setInt(8, pdto.getStock());
		this.ps.setString(9, pdto.getSale_status());
		this.ps.setString(10, pdto.getEarly_soldout());
		this.ps.setString(11, pdto.getMain_image_ori());
		this.ps.setString(12, pdto.getMain_image_path());
		this.ps.setString(13, pdto.getSub1_image_ori());
		this.ps.setString(14, pdto.getSub1_image_path());
		this.ps.setString(15, pdto.getSub2_image_ori());
		this.ps.setString(16, pdto.getSub2_image_path());
		this.ps.setString(17, pdto.getDetail_description());
		
		result = this.ps.executeUpdate();

        try {
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }
		
		return result;
    }
    
    public Map<String, Object> select_product_page(int pageno, int ctn) throws Exception{
		
		product_dto pdto = null;
		ArrayList<product_dto> view_all = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		
		//"select * from 테이블명 limit 시작번호, 갯수" 에서 시작번호는 0부터 시작, pageno(페이지번호)는 1부터 시작 
		int first_no = (pageno - 1) * ctn;  //각 페이지별 시작번호 

		this.con = db.getConnection();

		String sql_count = "select count(*) as total from product";
		this.ps = con.prepareStatement(sql_count);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		int total_ctn = this.rs.getInt("total");

		//최신 게시물부터 출력(역순 출력) : order by nidx desc
		String sql_pageall = "SELECT "
				+ "p.product_code, "
				+ "p.main_image_path, "
				+ "p.product_name, "
				+ "c.category_name, "
				+ "p.price, "
				+ "p.discount_price, "
				+ "p.discount_rate, "
				+ "p.stock, "
				+ "p.sale_status, "
				+ "p.early_soldout "
				+ "FROM product p  "
				+ "JOIN category c ON p.category_code = c.category_code "
				+ "ORDER BY p.pidx DESC "
				+ "LIMIT ?, ?"; 
		this.ps = con.prepareStatement(sql_pageall);
		this.ps.setInt(1, first_no);  //각 페이지별 시작번호 
		this.ps.setInt(2, ctn);       //페이지당 출력갯수 
		this.rs = this.ps.executeQuery();
		
		while(this.rs.next()) {
			pdto = new product_dto();
			pdto.setProduct_code(this.rs.getString("product_code"));
			pdto.setMain_image_path(this.rs.getString("main_image_path"));
			pdto.setProduct_name(this.rs.getString("product_name"));
			pdto.setCategory_name(this.rs.getString("category_name"));
			pdto.setPrice(this.rs.getInt("price"));
			pdto.setDiscount_price(this.rs.getInt("discount_price"));
			pdto.setDiscount_rate(this.rs.getInt("discount_rate"));
			pdto.setStock(this.rs.getInt("stock"));
			pdto.setSale_status(this.rs.getString("sale_status"));
			pdto.setEarly_soldout(this.rs.getString("early_soldout"));
			
			view_all.add(pdto);
		}
		
		result.put("total_ctn", total_ctn);
		result.put("view_all", view_all);
		
        try {
        	if (this.rs != null) this.rs.close();
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }		
	
		return result;
	}
    
public Map<String, Object> select_product_search(String search_key, String search_word, int ctn) throws Exception{
		
		product_dto pdto = null;
		ArrayList<product_dto> view_all = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();

		this.con = db.getConnection();

		String sql_count = "select count(*) as total from product";
		this.ps = con.prepareStatement(sql_count);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		int total_ctn = this.rs.getInt("total");

		//최신 게시물부터 출력(역순 출력) : order by nidx desc
		String sql_pageall = "SELECT "
				+ "p.product_code, "
				+ "p.main_image_path, "
				+ "p.product_name, "
				+ "c.category_name, "
				+ "p.price, "
				+ "p.discount_price, "
				+ "p.discount_rate, "
				+ "p.stock, "
				+ "p.sale_status, "
				+ "p.early_soldout "
				+ "FROM product p  "
				+ "JOIN category c ON p.category_code = c.category_code "
				+ "WHERE " + search_key + " LIKE ? "
				+ "ORDER BY p.pidx DESC "
				+ "LIMIT 0, ?"; 
		this.ps = con.prepareStatement(sql_pageall);
		this.ps.setString(1, "%" + search_word + "%");  //검색어에 와일드카드 추가 
		this.ps.setInt(2, ctn);  //출력 갯수 
		this.rs = this.ps.executeQuery();
		
		while(this.rs.next()) {
			pdto = new product_dto();
			pdto.setProduct_code(this.rs.getString("product_code"));
			pdto.setMain_image_path(this.rs.getString("main_image_path"));
			pdto.setProduct_name(this.rs.getString("product_name"));
			pdto.setCategory_name(this.rs.getString("category_name"));
			pdto.setPrice(this.rs.getInt("price"));
			pdto.setDiscount_price(this.rs.getInt("discount_price"));
			pdto.setDiscount_rate(this.rs.getInt("discount_rate"));
			pdto.setStock(this.rs.getInt("stock"));
			pdto.setSale_status(this.rs.getString("sale_status"));
			pdto.setEarly_soldout(this.rs.getString("early_soldout"));
			
			view_all.add(pdto);
		}
		
		result.put("total_ctn", total_ctn);
		result.put("view_all", view_all);
		
        try {
        	if (this.rs != null) this.rs.close();
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }		
	
		return result;
	}
}
