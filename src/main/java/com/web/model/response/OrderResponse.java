package com.web.model.response;

import java.util.ArrayList;
import java.util.List;

import com.web.entity.ProductEntity;

import lombok.Data;

@Data
public class OrderResponse {
	public static Long unitPrice;
	public static Long subTotal;
	public static Long totalQuantity;
	public static List<ProductEntity> productEntities = new ArrayList<>();
}
