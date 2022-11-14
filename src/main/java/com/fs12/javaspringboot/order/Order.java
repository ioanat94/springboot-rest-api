package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    @ElementCollection(targetClass = Integer.class)
    @Column(name="product_id", nullable=false)
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Integer> products;
    private double totalPrice;
    private String shippingAddress;

    public Order(Date date, String status, User customer, List<Integer> products, double totalPrice, String shippingAddress) {
        this.date = date;
        this.status = status;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }
}
