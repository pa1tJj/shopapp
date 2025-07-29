package com.web.enums;

import java.util.HashMap;
import java.util.Map;

public enum DistrictType {
	BA_DINH("Ba Đình"),
	HOAN_KIEM("Hoàn Kiếm"),
	TAY_HO("Tây Hồ"),
	LONG_BIEN("Long Biên"),
	CAU_GIAY("Cầu Giấy"),
	DONG_DA("Đống Đa"),
	HAI_BA_TRUNG("Hai Bà Trưng"),
	HOANG_MAI("Hoàng Mai"),
	THANH_XUAN("Thanh Xuân"),
	NAM_TU_LIEM("Nam Từ Liêm"),
	BAC_TU_LIEM("Bắc Từ Liêm"),
	HA_DONG("Hà Đông"),
	THANH_TRI("Thanh Trì"),
	THANH_OAI("Thanh Oai"),
	DAN_PHUONG("Đan Phượng"),
	HOAI_DUC("Hoài Đức"),
	DONG_ANH("Đông Anh"),
	GIA_LAM("Gia Lâm"),
	ME_LINH("Mê Linh"),
	PHU_XUYEN("Phú Xuyên"),
	PHUC_THAO("Phúc Thọ"),
	QUOC_OAI("Quốc Oai"),
	SOC_SON("Sóc Sơn"),
	THACH_THAT("Thạch Thất"),
	THANH_BA("Thanh Ba"),
	THANH_MY("Thị xã Sơn Tây"),
	THUONG_TIN("Thường Tín"),
	UNG_HOA("Ứng Hòa");
	
	private final String name;
	private DistrictType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(DistrictType item : DistrictType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
