package com.web.model.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private Long userId;
	private List<Long> productId = new ArrayList<>();
	private Long quantity;
	private boolean isChecked;
}
