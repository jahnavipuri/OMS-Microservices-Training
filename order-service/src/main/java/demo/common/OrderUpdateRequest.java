package demo.common;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderUpdateRequest {
    @NotEmpty
    private List<OrderItem> orderItems;
    @NotNull
    private Long storeId;

}
