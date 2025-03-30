package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.m_dbinfo;

public class category_query {
	
	private m_dbinfo db = new m_dbinfo(); 
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public int insert_category(category_dto cdto) throws Exception{
    	
    	int result = 0;
 
		this.con = db.getConnection();
		String sql = "insert into category values ('0',?,?,now())";
		this.ps = con.prepareStatement(sql);
		this.ps.setString(1, cdto.getCategory_code());
		this.ps.setString(2, cdto.getCategory_name());
		
		result = this.ps.executeUpdate();

        try {
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }
		
		return result;
    }
    
    public ArrayList<category_dto> select_category_all() throws Exception {
    	
    	category_dto cdto = null;
    	ArrayList<category_dto> category_all = new ArrayList<>();
    	
		this.con = this.db.getConnection();

		String sql_all = "select category_code,category_name from category"; 
		this.ps = con.prepareStatement(sql_all);
		this.rs = this.ps.executeQuery();
		
		while(this.rs.next()) {
			cdto = new category_dto();
			cdto.setCategory_code(this.rs.getString("category_code"));
			cdto.setCategory_name(this.rs.getString("category_name"));
			
			category_all.add(cdto);
		}

        try {
        	if (this.rs != null) this.rs.close();
            if (this.ps != null) this.ps.close();
            if (this.con != null) this.con.close();
        } catch (Exception e) {
            e.printStackTrace(); // 자원 해제 실패 시 출력
        }	
    	
    	return category_all;
    }
    
    public Map<String, Object> select_category_page(int pageno, int ctn) throws Exception {
		
		category_dto cdto = null;
		ArrayList<category_dto> view_all = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		
		//"select * from 테이블명 limit 시작번호, 갯수" 에서 시작번호는 0부터 시작, pageno(페이지번호)는 1부터 시작 
		int first_no = (pageno - 1) * ctn;  //각 페이지별 시작번호 

		this.con = this.db.getConnection();

		String sql_count = "select count(*) as total from category";
		this.ps = con.prepareStatement(sql_count);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		int total_ctn = this.rs.getInt("total");
		
		//최신 등록부터 출력(역순 출력) : order by nidx desc
		String sql_pageall = "select * from category order by cidx desc limit ?,?"; 
		this.ps = con.prepareStatement(sql_pageall);
		this.ps.setInt(1, first_no);  //각 페이지별 시작번호 
		this.ps.setInt(2, ctn);       //페이지당 출력갯수 
		this.rs = this.ps.executeQuery();
		
		while(this.rs.next()) {
			cdto = new category_dto();
			cdto.setCidx(this.rs.getInt("cidx"));
			cdto.setCategory_code(this.rs.getString("category_code"));
			cdto.setCategory_name(this.rs.getString("category_name"));
			
			view_all.add(cdto);
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
    
public Map<String, Object> select_category_search(String search_key, String search_word, int ctn) throws Exception {
		
		category_dto cdto = null;
		ArrayList<category_dto> view_all = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();

		this.con = this.db.getConnection();

		String sql_count = "select count(*) as total from category";
		this.ps = con.prepareStatement(sql_count);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		int total_ctn = this.rs.getInt("total");
		//최신 등록부터 출력(역순 출력) : order by nidx desc
		String sql_pageall = "select * from category where " + search_key + " like ? order by cidx desc limit 0,?"; 
		this.ps = con.prepareStatement(sql_pageall);
		this.ps.setString(1, "%" + search_word + "%");  //검색어에 와일드카드 추가 
		this.ps.setInt(2, ctn);  //출력 갯수 
		this.rs = this.ps.executeQuery();
		
		while(this.rs.next()) {
			cdto = new category_dto();
			cdto.setCidx(this.rs.getInt("cidx"));
			cdto.setCategory_code(this.rs.getString("category_code"));
			cdto.setCategory_name(this.rs.getString("category_name"));
			
			view_all.add(cdto);
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
