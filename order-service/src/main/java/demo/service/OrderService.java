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
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    public final String INVENTORY_SERVICE = "http://INVENTORY-SERVICE/inventory";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private RestTemplate template;

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
        Order createdOrder = orderRepository.save(order);


        List<OrderDetails> orderItems = request.getOrderItems().stream().map(orderItem -> {
            OrderDetails orderDetails = new OrderDetails();
            BeanUtils.copyProperties(orderItem, orderDetails);
            orderDetails.setOrderNo(createdOrder.getOrderNo());
            return orderDetails;
        }).collect(Collectors.toList());
        orderDetailsRepository.saveAll(orderItems);
    }
}
