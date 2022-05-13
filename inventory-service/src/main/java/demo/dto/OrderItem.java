package demo.dto;

import lombok.Data;

@Data
public class OrderItem {
    private Long storeId;
    private Long productId;
    private Long quantity;
}
