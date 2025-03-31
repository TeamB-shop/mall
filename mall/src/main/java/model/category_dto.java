package model;

public class category_dto {
	int cidx;
	String category_code, category_name;
	String cdate;
	
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String cate_code) {
		this.category_code = cate_code;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String cate_name) {
		this.category_name = cate_name;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
}
