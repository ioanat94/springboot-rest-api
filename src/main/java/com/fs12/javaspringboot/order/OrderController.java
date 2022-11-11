package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(path = "{orderId}")
    public Optional<Order> getOrder(@PathVariable("orderId") int orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "{orderId}")
    public void updateOrder(@PathVariable("orderId") int orderId,
                              @RequestBody Order order) {
        orderService.updateOrder(orderId, order);
    }
}
