package demo.common;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequest {
    @NotEmpty
    private List<OrderItem> orderItems;
    @NotNull
    private Double orderAmount;
    @NotNull
    private String customerId;
    @NotNull
    private String billingAddress1;
}
