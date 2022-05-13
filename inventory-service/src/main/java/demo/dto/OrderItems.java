package demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderItems {

    List<OrderItem> orderItems;

}
