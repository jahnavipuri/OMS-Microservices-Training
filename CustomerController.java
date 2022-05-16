package demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import demo.entity.Customer;

import demo.service.CustomerService;

public class CustomerController {
private CustomerService service;
@PostMapping("/add")
public Customer addCustomer(@RequestBody Customer customer) {
	return service.addCustomer(customer);
}
}
