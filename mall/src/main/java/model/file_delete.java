package model;

import java.io.File;

public class file_delete {
	
	public void f_delete(product_dto pdto, String webapp_path) {
		
		if(pdto.getMain_image_path() != null) {
			File main_img = new File(webapp_path + pdto.getMain_image_path());
			if (main_img.exists()) {
				main_img.delete();
			}
		}
		if(pdto.getSub1_image_path() != null) {
			File sub1_img = new File(webapp_path + pdto.getSub1_image_path());
			if (sub1_img.exists()) {
				sub1_img.delete();
			}
		}
		if(pdto.getMain_image_path() != null) {
			File sub2_img = new File(webapp_path + pdto.getSub2_image_path());
			if (sub2_img.exists()) {
				sub2_img.delete();
			}
		}
	}
}
