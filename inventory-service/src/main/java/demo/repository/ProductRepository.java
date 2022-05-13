package demo.repository;

import demo.entity.Product;
import demo.entity.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
