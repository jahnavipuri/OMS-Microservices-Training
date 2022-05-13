package demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INVENTORY_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreInventory
{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long inventoryId;
	private Long storeId;
	private Long productId;
	private Long quantity;
}
