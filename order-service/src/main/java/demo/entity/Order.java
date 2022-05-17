package demo.entity;

import demo.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNo;
    private Long storeId;
    @NotNull
    private Date orderDate;
    @NotNull
    private Double orderAmount;
    @NotNull
    private Long customerId;
    @NotNull
    private String billingAddress1;
    @NotNull
    private OrderStatus orderStatus;
    private Date lastUpdated;
    private String lastUpdateBy;
}
