package demo.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderItem {

    @NotNull
    private Long productId;
    @NotNull
    private Long storeId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;
    @NotNull
    private String status;
    private Date lastUpdated;
    private String lastUpdateBy;
}
