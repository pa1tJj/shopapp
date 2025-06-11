package com.web.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.web.entity.ProductEntity;
import com.web.model.request.ProductRequest;
import com.web.repository.custom.ProductRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	public static void queryNormal(ProductRequest productRequest, StringBuilder sql) {
		try {
			Field[] fields = ProductRequest.class.getDeclaredFields();
			for (Field it : fields) {
				it.setAccessible(true);
				String fieldName = it.getName();
				if(!fieldName.equals("priceFrom") && !fieldName.equals("priceTo") && !fieldName.equals("stockQuantity") && !fieldName.equals("isFeatured")) {
					Object value = it.get(productRequest);
					if(value != null) {
						if(it.getType().getName().equals("java.lang.Long") || it.getType().getName().equals("java.lang.Integer")) {
							sql.append(" AND p." + fieldName + "=" + value);
						} else if (it.getType().getName().equals("java.lang.String") && !value.equals("")) {
							sql.append(" AND p." + fieldName + " LIKE '%" + value + "%'");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void querySpecial(ProductRequest productRequest, StringBuilder sql) {
		if(productRequest.getPriceFrom() != null || productRequest.getPriceTo() != null) {
			if(productRequest.getPriceFrom() != null) {
				sql.append(" AND p.price >=" + productRequest.getPriceFrom());
			}
			if(productRequest.getPriceTo() != null) {
				sql.append(" AND p.price <=" + productRequest.getPriceTo());
			}
		}
		if(productRequest.getStockQuantity() != null) {
			sql.append(" AND p.stock_quantity >=" + productRequest.getStockQuantity());
		}
		String status = productRequest.getIsFeatured();
		if(status != null && !status.equals("")) {
			if(status.equals("DANG_BAN")) {
				sql.append(" AND p.is_featured = 1");
			} else if(status.equals("NGUNG_BAN")){
				sql.append(" AND p.is_featured = 0");
			}
		}
	}
	
	@Override
	public List<ProductEntity> findAll(ProductRequest productRequest, Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT *FROM product p ");
		StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		queryNormal(productRequest, where);
		querySpecial(productRequest, where);
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), ProductEntity.class);
		return query.getResultList();
	}

}
