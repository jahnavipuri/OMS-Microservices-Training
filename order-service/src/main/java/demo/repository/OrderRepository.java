package demo.repository;

import demo.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long>
{
    List<Order> findByStoreId(Long storeId);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
