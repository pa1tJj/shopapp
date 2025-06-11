package com.web.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.converter.ProductConverter;
import com.web.entity.ProductEntity;
import com.web.model.request.ProductRequest;
import com.web.model.response.ProductResponse;
import com.web.repository.ProductRepository;
import com.web.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public List<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable) {
		List<ProductEntity> products = productRepository.findAll(productRequest, pageable);
		List<ProductResponse> result = new ArrayList<>();
		for (ProductEntity item : products) {
			ProductResponse productResponse = productConverter.toProductResponse(item);
			result.add(productResponse);
		}
		return result;
	}
	
	private static final Path CURRENT_FOLDER = Paths.get("src/main/resources");

	public String updateImage(ProductRequest productRequest) {
		Path staticPath = Paths.get("static");
		Path imagePath = Paths.get("images");
		if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
			try {
				Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)
				.resolve(productRequest.getImage().getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(file)) {
			os.write(productRequest.getImage().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productRequest.getImage().getOriginalFilename().toString();
	}
}
