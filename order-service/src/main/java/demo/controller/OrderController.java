package demo.controller;

import demo.entity.Order;
import demo.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.common.*;
import demo.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	private OrderService service;

	@PostMapping("/create")
	public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request)
	{
		 service.createOrder(request);
		 return ResponseEntity.ok().build();
	}

	@PostMapping("/updateStatus/cancel/{orderNum}")
	public ResponseEntity<Void> cancelOrder(@PathVariable Long orderNum)
	{
		service.cancelOrder(orderNum);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
		Order order = service.getOrderById(orderId).orElse(null);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/store/{storeId}")
	public ResponseEntity<List<Order>> getOrderByStoreId(@PathVariable Long storeId) {
		List<Order> orders = service.getOrderByStoreId(storeId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable Long customerId) {
		List<Order> orders = service.getOrderByCustomerId(customerId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/status/{orderStatus}")
	public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus orderStatus) {
		List<Order> orders = service.getOrdersByStatus(orderStatus);
		return ResponseEntity.ok(orders);
	}




}
