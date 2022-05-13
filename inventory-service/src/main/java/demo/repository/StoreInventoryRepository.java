package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.*;

public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Integer> {

    StoreInventory findByProductIdAndStoreId(Long productId,Long storeId);
}
