package demo.controller;


import demo.dto.OrderItem;
import demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService service;

    @PostMapping("/reserveProduct")
    public void reserveProductsForOrder(@RequestBody List<OrderItem> items) {
        service.reserveProductsForOrder(items);
    }
}
