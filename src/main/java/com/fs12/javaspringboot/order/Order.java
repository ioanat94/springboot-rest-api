package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.product.Product;
import com.fs12.javaspringboot.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private int id;
    private Date date;
    private String status;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id")
    private User customer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ElementCollection(targetClass = Product.class)
    private List<Product> products;
    private double totalPrice;
    private String shippingAddress;

    public Order(Date date, String status, User customer, ArrayList<Product> products, double totalPrice, String shippingAddress) {
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }
}
