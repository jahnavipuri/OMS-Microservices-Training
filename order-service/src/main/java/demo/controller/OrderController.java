package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.common.*;
import demo.service.OrderService;

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
}
