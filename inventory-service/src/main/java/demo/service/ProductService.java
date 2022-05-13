package demo.service;

import demo.dto.ProductCreateReqDto;
import demo.entity.Product;
import demo.repository.ProductRepository;
import demo.repository.StoreInventoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreInventoryRepository storeInventoryRepository;

    public Product addProduct(ProductCreateReqDto request) {
        validateRequest(request);
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        if (request.getProductId() != null && productRepository.existsById(request.getProductId())) {
            inventoryService.addProductToStoreInventory(request);
        } else {
            product = productRepository.save(product);
            request.setProductId(product.getProductId());
            inventoryService.addProductToStoreInventory(request);
        }
        return product;
    }

    private void validateRequest(ProductCreateReqDto request) {
        storeService.findStoreByStoreId(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Store"));
    }


}
