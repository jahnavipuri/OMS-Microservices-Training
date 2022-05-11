package demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "order_details_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails
{
    @Id
    private String orderNo;
    private String productId;
	private Integer quantity;
	private Double price;
	private String status;
	private Date lastUpdated;
	private String LastUpdateBy;
}
