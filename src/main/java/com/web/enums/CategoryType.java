package com.web.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryType {
	TRUYEN_THONG("Truyền thống"),
	DAC_BIET("Đặc biệt"),
	CHAY("Chay"),
	CAO_CAP("Cao cấp");
	
	private final String name;
	
	private CategoryType(String name) {
		this.name = name;
	}
	
	public String getCategoryName() {return name;}
	public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(CategoryType item : CategoryType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
