package demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderDetailsId;
	@NotNull
    private Long orderNo;
	@NotNull
    private Long productId;
	@NotNull
	private Integer quantity;
	@NotNull
	private Double price;
	@NotNull
	private String status;
	private Date lastUpdated;
	private String lastUpdateBy;
}
