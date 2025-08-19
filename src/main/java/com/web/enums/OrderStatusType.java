package com.web.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatusType {
	PENDING("Chờ xác nhận"),

	CONFIRMED("Đơn đã được xác nhận bởi hệ thống"),

	PROCESSING("Đang chuẩn bị hàng "),

	SHIPPED("Đã giao cho đơn vị vận chuyển"),

	DELIVERED("Khách đã nhận hàng thành công"),

	CANCELLED("Đơn hàng bị hủy (khách hoặc hệ thống)"),

	RETURNED("Khách trả hàng hoặc hoàn tiền");
	
	private final String name;
	private OrderStatusType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(OrderStatusType item : OrderStatusType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }

}
