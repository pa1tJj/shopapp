package com.web.service.web.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.web.converter.OrderConverter;
import com.web.entity.CartEntity;
import com.web.entity.OrderDetail;
import com.web.entity.OrderEntity;
import com.web.entity.ProductEntity;
import com.web.model.dto.OrderDTO;
import com.web.model.dto.OrderDTOs;
import com.web.model.dto.OrderDetailDTO;
import com.web.model.response.OrderResponse;
import com.web.model.response.PaymentSuccessResponse;
import com.web.repository.web.CartProductRepository;
import com.web.repository.web.CartRepository;
import com.web.repository.web.OrderRepository;
import com.web.service.web.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderConverter orderConverter;
	private CartRepository cartRepository;
	private CartProductRepository cartProductRepository;

	@Override
	public void getPlaceOrder(OrderDTO orderDTO) {
		OrderEntity orderEntity = orderConverter.getOrderEntity(orderDTO);
		orderRepository.save(orderEntity);	
		CartEntity cartEntity = cartRepository.findByUserId(orderDTO.getUserId());
		for(ProductEntity item : OrderResponse.productEntities) {
			cartProductRepository.deleteByCartIdAndProductId(cartEntity.getId(), item.getId());
		}
	}

	@Override
	public PaymentSuccessResponse getResponse(Long userId) {
		PaymentSuccessResponse paymentSuccessResponse = new PaymentSuccessResponse();
		OrderEntity orderEntity = orderRepository.findTopByOrderByIdDesc();
		Optional<OrderEntity> optional = orderRepository.findByIdAndUserId(orderEntity.getId() ,userId);
		paymentSuccessResponse.setOrderStatus(optional.get().getOrderStatus());
		paymentSuccessResponse.setPaymentMethod(optional.get().getPaymentMethod());
		paymentSuccessResponse.setRecipientName(optional.get().getRecipentName());
		paymentSuccessResponse.setRecipientPhone(optional.get().getRecipentPhone());
		paymentSuccessResponse.setShippingAddress(optional.get().getShippingAddress());
		Long totalPrice = 0L;
		for(OrderDetail item : optional.get().getOrderDetails()) {
			totalPrice += item.getSubTotal();
		}
		paymentSuccessResponse.setTotalPrice(NumberFormat.getNumberInstance(Locale.US).format(totalPrice).replace(",", "."));
		return paymentSuccessResponse;
	}

	@Override
	public List<OrderDTOs> getOrdersByUser(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);

        return orders.stream().map(order -> {
            List<OrderDetailDTO> detailDTOs = order.getOrderDetails()
                    .stream()
                    .map(detail -> new OrderDetailDTO(
                            detail.getProduct().getName(),
                            detail.getProduct().getImageUrl(),
                            detail.getQuantity(),
                            detail.getUnitPrice(),
                            detail.getSubTotal()
                    ))
                    .collect(Collectors.toList());

            return new OrderDTOs(
                    order.getId(),
                    order.getRecipentName(),
                    order.getRecipentPhone(),
                    order.getShippingAddress(),
                    order.getPaymentMethod(),
                    order.getOrderStatus(),
                    order.getTotalAmount(),
                    detailDTOs
            );
        }).collect(Collectors.toList());
    }
}

