package com.web.service.web.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.entity.CartEntity;
import com.web.entity.CartProductEntity;
import com.web.entity.ProductEntity;
import com.web.entity.UserEntity;
import com.web.model.dto.CartProductDTO;
import com.web.model.dto.PriceDTO;
import com.web.model.request.CartRequest;
import com.web.model.request.OrderRequest;
import com.web.model.response.OrderResponse;
import com.web.repository.ProductRepository;
import com.web.repository.UserRepository;
import com.web.repository.web.CartProductRepository;
import com.web.repository.web.CartRepository;
import com.web.service.web.CartService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
	
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private CartProductRepository cartProductRepository;
	
	@Override
	public void addProductToCart(CartRequest cartRequest) {
		UserEntity userEntity = userRepository.findById(cartRequest.getUserId()).get();
		CartEntity cartEntity = cartRepository.findByUser(userEntity);
		if(cartEntity == null) {
			cartEntity = new CartEntity();
			cartEntity.setUser(userEntity);
			cartEntity.getUser().setId(cartRequest.getUserId());
			cartRepository.save(cartEntity);
		} 
		ProductEntity productEntity = productRepository.findById(cartRequest.getProductId()).get();
		Optional<CartProductEntity> existingProduct = cartProductRepository.findByCartAndProduct(cartEntity, productEntity);
		if (existingProduct.isEmpty()) {
			CartProductEntity cartProductEntity = new CartProductEntity();
			cartProductEntity.setCart(cartEntity);
			cartProductEntity.setProduct(productEntity);
			cartProductEntity.setQuantity(cartRequest.getQuantity());
			cartProductRepository.save(cartProductEntity);
		} else {
			CartProductEntity cartProductEntity = existingProduct.get();
			cartProductEntity.setQuantity(cartRequest.getQuantity());
			cartProductRepository.save(cartProductEntity);
		}
				
	}

	@Override
	public List<CartProductDTO> getUserCartProducts(Long id) {
		CartEntity cart = cartRepository.findByUserId(id);
		List<CartProductEntity> product = cartProductRepository.findByCart(cart);
		List<CartProductDTO> cartProductDTOs = new ArrayList<CartProductDTO>();
		for(CartProductEntity item : product) {
			CartProductDTO cartProductDTO = new CartProductDTO();
			cartProductDTO.setProductEntity(item.getProduct());
			cartProductDTO.setQuantity(item.getQuantity());
			cartProductDTO.setPriceTotal(NumberFormat.getNumberInstance(Locale.US).format(cartProductDTO.getQuantity() * item.getProduct().getPrice()).replace(",", ".") + "đ");
			cartProductDTOs.add(cartProductDTO);
		}
		
		return cartProductDTOs;
	}

	@Override
	public void deleteByCartAndProduct(CartRequest cartRequest) {
		CartEntity cartEntity = cartRepository.findByUserId(cartRequest.getUserId());
		ProductEntity productEntity = productRepository.findById(cartRequest.getProductId()).get();
		Optional<CartProductEntity> existingProduct = cartProductRepository.findByCartAndProduct(cartEntity, productEntity);
		cartProductRepository.delete(existingProduct.get());
	}

	@Override
	public String getTotalAmount(OrderRequest orderRequest) {
		CartEntity cartEntity = cartRepository.findByUserId(orderRequest.getUserId());
		Long totalAmount = 0L;
		List<ProductEntity> productEntities = productRepository.findByIdIn(orderRequest.getProductId());
		for(ProductEntity item : productEntities) {
			Optional<CartProductEntity> existingProduct = cartProductRepository.findByCartAndProduct(cartEntity, item);
			totalAmount += existingProduct.get().getQuantity() * existingProduct.get().getProduct().getPrice();
		}
		return NumberFormat.getNumberInstance(Locale.US).format(totalAmount).replace(",", ".") + "đ";
	}

	@Override
	public List<CartProductDTO> findProductOrder(Long userId, List<Long> productId) {
		CartEntity cartEntity = cartRepository.findByUserId(userId);
		List<ProductEntity> productEntities = productRepository.findByIdIn(productId);
		List<CartProductDTO> result = new ArrayList<>();
		Long price = 0L;
		Long total = 0L;
		List<ProductEntity> entities = new ArrayList<ProductEntity>();
		for(ProductEntity item : productEntities) {
			CartProductDTO cartProductDTO = new CartProductDTO();
			Optional<CartProductEntity> existingProduct = cartProductRepository.findByCartAndProduct(cartEntity, item);
			cartProductDTO.setProductEntity(item);
			cartProductDTO.setQuantity(existingProduct.get().getQuantity());
			price += cartProductDTO.getQuantity() * item.getPrice();
			cartProductDTO.setPriceTotal(NumberFormat.getNumberInstance(Locale.US).format(cartProductDTO.getQuantity() * item.getPrice()).replace(",", ".") + "đ");
			PriceDTO.priceAll = NumberFormat.getNumberInstance(Locale.US).format(price).replace(",", ".") + "đ";
			PriceDTO.pricePayment = (NumberFormat.getNumberInstance(Locale.US).format(price + 20000).replace(",", ".") + "đ");	
			PriceDTO.totalPayment = price + 20000;
			entities.add(item);
			total += cartProductDTO.getQuantity();
			result.add(cartProductDTO);
		}
		OrderResponse.productEntities = entities;
		OrderResponse.totalQuantity = total;
		return result;
	}

}
