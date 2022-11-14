package com.fs12.javaspringboot.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderProductsDTO {
    private int orderId;
    private List<String> name = new ArrayList<>();
    private List<String> variant = new ArrayList<>();
    private List<String> size = new ArrayList<>();
    private List<Double> price = new ArrayList<>();
}
