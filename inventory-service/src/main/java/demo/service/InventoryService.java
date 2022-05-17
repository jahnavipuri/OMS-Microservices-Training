package demo.service;

import demo.dto.OrderItem;
import demo.dto.ProductCreateReqDto;
import demo.entity.StoreInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.repository.StoreInventoryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private StoreInventoryRepository storeInventoryRepository;

    public StoreInventory addProductToStoreInventory(ProductCreateReqDto request) {
        StoreInventory storeInventory = storeInventoryRepository.findByProductIdAndStoreId(request.getProductId(), request.getStoreId());
        if (storeInventory != null) {
            storeInventory.setQuantity(storeInventory.getQuantity() + 1);
        } else {
            storeInventory = new StoreInventory();
            storeInventory.setQuantity(1L);
        }
        storeInventory.setStoreId(request.getStoreId());
        storeInventory.setProductId(request.getProductId());
        return storeInventoryRepository.save(storeInventory);
    }

    public void reserveProductsForOrder(List<OrderItem> request) {
        checkStockAvailability(request);
        updateStock(request);
    }

    private void checkStockAvailability(List<OrderItem> request) {
        request.forEach(orderItem -> {
            StoreInventory stockInventory = storeInventoryRepository.
                    findByProductIdAndStoreId(orderItem.getProductId(), orderItem.getStoreId());
            if (stockInventory==null || stockInventory.getQuantity() < orderItem.getQuantity()) {
                throw new IllegalArgumentException("Stock unavailable for orderId " + orderItem.getProductId());
            }
        });
    }

    private void updateStock(List<OrderItem> request) {
        request.forEach(orderItem -> {
            StoreInventory stockInventory = storeInventoryRepository.
                    findByProductIdAndStoreId(orderItem.getProductId(), orderItem.getStoreId());
            stockInventory.setQuantity(stockInventory.getQuantity() - orderItem.getQuantity());
        });
    }

    public void cancelOrder(List<OrderItem> items) {

    }
}
