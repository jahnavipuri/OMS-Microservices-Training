package demo.controller;


import demo.entity.Product;
import demo.entity.Store;
import demo.service.InventoryService;
import demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController
{
	@Autowired
	private StoreService service;
	
//	@PostMapping("/checkInventory")
//	public StoreInventory doPayment(@RequestBody StoreInventory storeInventory)
//	{
//		System.out.println("doPayment");
//		return service.doPayment(storeInventory);
//	}

	@PostMapping("/add")
	public Store addStore(@RequestBody Store store) {
		return service.addStore(store);
	}
}
