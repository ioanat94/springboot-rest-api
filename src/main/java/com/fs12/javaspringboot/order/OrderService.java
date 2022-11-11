package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder(int orderId) {
        return orderRepository.findById(orderId);
    }

    public void addOrder(Order order) {
        order.setDate(new Date());
        orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        boolean exists = orderRepository.existsById(orderId);

        if(!exists) {
            throw new IllegalStateException("Product with id " + orderId + " does not exist.");
        }

        orderRepository.deleteById(orderId);
    }

    @Transactional
    public void updateOrder(int orderId, Order order) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with id " + orderId + " does not exist."));

        if(order.getStatus() != null && order.getStatus().length() > 0 && !Objects.equals(foundOrder.getStatus(), order.getStatus())) {
            foundOrder.setStatus(order.getStatus());
        }
    }
}
