package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Order {
    private Date date;
    private Status status;
    private String user;
    private Product[] products;
    private double totalPrice;
    private String shippingAddress;
}
