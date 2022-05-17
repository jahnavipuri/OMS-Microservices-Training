package demo.repository;

import demo.entity.Order;
import demo.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long>
{
    List<OrderDetails> findByOrderNo(Long orderNum);
}
