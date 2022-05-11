package demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String orderNo;
    private String storeId;
    private Date orderDate;
    private Double orderAmount;
    private String customerId;
    private String billingAddress1;
    private String billingAddress2;
    private String billingState;
    private String billingCountry;
    private String billingAddressPincode;
    private String billingPhone;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingState;
    private String shippingCountry;
    private String shippingAddressPincode;
    private String shippingPhone;
    private String orderStatus;
    private Date lastUpdated;
    private String LastUpdateBy;
}
