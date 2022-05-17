package demo.service;

import demo.common.OrderRequest;
import demo.entity.Order;
import demo.entity.OrderDetails;
import demo.enums.OrderStatus;
import demo.repository.OrderDetailsRepository;
import demo.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    public static final String INVENTORY_SERVICE = "http://INVENTORY-SERVICE/inventory";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private RestTemplate template;

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrderByStoreId(Long storeId) {
        return orderRepository.findByStoreId(storeId);
    }

    public List<Order> getOrderByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    public void createOrder(OrderRequest request) {
        String url = INVENTORY_SERVICE + "/reserveProduct";
        try {
            ResponseEntity<Object> objectResponseEntity = template.postForEntity(url, request.getOrderItems(), Object.class);
            if (objectResponseEntity.getStatusCodeValue() != 200) {
                throw new IllegalArgumentException("Order not placed something is wrong");
            }
        } catch (HttpClientErrorException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderDate(new Date());
        order.setStoreId(request.getStoreId());
        Order createdOrder = orderRepository.save(order);

        request.getOrderItems().forEach(orderItem -> {
            OrderDetails orderDetails = new OrderDetails();
            BeanUtils.copyProperties(orderItem, orderDetails);
            orderDetails.setOrderNo(createdOrder.getOrderNo());
            orderDetailsRepository.save(orderDetails);
        });
    }
    public void cancelOrder(Long orderNum) {
        Order order = orderRepository.findById(orderNum)
                .orElseThrow(() -> new IllegalArgumentException("Order does not exists"));

        order.setOrderStatus(OrderStatus.CANCELLED);

        List<OrderDetails> orderedItems = orderDetailsRepository.findByOrderNo(orderNum);

        if(!orderedItems.isEmpty()) {
            String url = INVENTORY_SERVICE + "/cancelOrder";
            try {
                ResponseEntity<Object> objectResponseEntity = template.postForEntity(url, orderedItems, Object.class);
                if (objectResponseEntity.getStatusCodeValue() != 200) {
                    throw new IllegalArgumentException("Order not placed something is wrong");
                }
            } catch (HttpClientErrorException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
    }
}
