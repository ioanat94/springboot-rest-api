package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.util.OrderNotFoundException;
import com.fs12.javaspringboot.util.OrdersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderUserDTO> getCustomers() {
        return orderRepository.findAll().stream().map(this::convertEntityToUserDto).collect(Collectors.toList());
    }

    public List<OrderProductsDTO> getProducts() {
        return orderRepository.findAll().stream().map(this::convertEntityToProductDto).collect(Collectors.toList());
    }

    private OrderUserDTO convertEntityToUserDto(Order order) {
        OrderUserDTO orderUserDTO = new OrderUserDTO();
        orderUserDTO.setOrderId(order.getId());
        orderUserDTO.setEmail(order.getCustomer().getEmail());

        return orderUserDTO;
    }

    private OrderProductsDTO convertEntityToProductDto(Order order) {
        OrderProductsDTO orderProductsDTO = new OrderProductsDTO();

        List<String> names = new ArrayList<>();
        List<String> variants = new ArrayList<>();
        List<String> sizes = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        orderProductsDTO.setOrderId(order.getId());

        order.getProducts().forEach(product -> {
            names.add(product.getName());
            variants.add(product.getVariant());
            sizes.add(product.getSize());
            prices.add(product.getPrice());
            }
        );

        orderProductsDTO.setName(names);
        orderProductsDTO.setVariant(variants);
        orderProductsDTO.setSize(sizes);
        orderProductsDTO.setPrice(prices);

        System.out.println(orderProductsDTO);

        return orderProductsDTO;
    }

    public List<Order> getOrders() throws OrdersNotFoundException {
        List<Order> orders = orderRepository.findAll();

        if (!orders.isEmpty()) {
            return orders;
        } else {
            throw new OrdersNotFoundException("No orders found.");
        }
    }

    public Optional<Order> getOrder(int orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent()) {
            return order;
        } else {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }
    }

    public Order addOrder(Order order) {
        order.setDate(new Date());
        return orderRepository.save(order);
    }

    public String deleteOrder(int orderId) throws OrderNotFoundException {
        boolean exists = orderRepository.existsById(orderId);

        if(!exists) {
            throw new OrderNotFoundException("Order with id " + orderId + " does not exist.");
        }

        orderRepository.deleteById(orderId);

        return "Order with id " + orderId + " deleted successfully.";
    }

    @Transactional
    public Order updateOrder(int orderId, Order order) throws OrderNotFoundException {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " does not exist."));

        if(order.getStatus() != null && order.getStatus().length() > 0 && !Objects.equals(foundOrder.getStatus(), order.getStatus())) {
            foundOrder.setStatus(order.getStatus());
        }

        return foundOrder;
    }
}
