package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.util.OrderNotFoundException;
import com.fs12.javaspringboot.util.OrdersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(path = "/order-user")
    public ResponseEntity<List<OrderUserDTO>> getCustomers() {
        return ResponseEntity.ok(orderService.getCustomers());
    }

    @GetMapping(path = "/order-products")
    public ResponseEntity<List<OrderProductsDTO>> getProducts() {
        return ResponseEntity.ok(orderService.getProducts());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() throws OrdersNotFoundException {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping(path = "/sort/{field}")
    public ResponseEntity<List<Order>> getOrdersWithSorting(@PathVariable String field) throws OrdersNotFoundException {
        return ResponseEntity.ok(orderService.getOrdersWithSorting(field));
    }

    @GetMapping(path = "{orderId}")
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order) {
        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }

    @PutMapping(path = "{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") int orderId,
                              @RequestBody Order order) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.updateOrder(orderId, order), HttpStatus.OK);
    }
}
