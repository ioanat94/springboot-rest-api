package com.fs12.javaspringboot.order;

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
