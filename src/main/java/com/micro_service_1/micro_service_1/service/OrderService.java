package com.micro_service_1.micro_service_1.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro_service_1.micro_service_1.dao.OrderRepository;
import com.micro_service_1.micro_service_1.dto.OrderLineItemsDto;
import com.micro_service_1.micro_service_1.dto.OrderRequest;
import com.micro_service_1.micro_service_1.models.Order;
import com.micro_service_1.micro_service_1.models.OrderLineItems;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
	   List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		   .stream()
		   .map(orderLineItemsDTo -> mapToDto(orderLineItemsDTo)).toList();
		
	   order.setOrderLineItemsList(orderLineItems);
	   
	   orderRepository.save(order);
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDTo) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDTo.getPrice());
		orderLineItems.setQuantity(orderLineItemsDTo.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDTo.getSkuCode());
		return orderLineItems;
	}
}
