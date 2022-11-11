package com.fs12.javaspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        boolean exists = productRepository.existsById(productId);

        if(!exists) {
            throw new IllegalStateException("Product with id " + productId + " does not exist.");
        }

        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(int productId, Product product) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id " + productId + " does not exist."));

        if(product.getName() != null && product.getName().length() > 0 && !Objects.equals(foundProduct.getName(), product.getName())) {
            foundProduct.setName(product.getName());
        }

        if(product.getImage() != null && product.getImage().length() > 0 && !Objects.equals(foundProduct.getImage(), product.getImage())) {
            foundProduct.setImage(product.getImage());
        }

        if(product.getDescription() != null && product.getDescription().length() > 0 && !Objects.equals(foundProduct.getDescription(), product.getDescription())) {
            foundProduct.setDescription(product.getDescription());
        }

        if(product.getPet() != null && product.getPet().length() > 0 && !Objects.equals(foundProduct.getPet(), product.getPet())) {
            foundProduct.setPet(product.getPet());
        }

        if(product.getSubcategory() != null && product.getSubcategory().length() > 0 && !Objects.equals(foundProduct.getSubcategory(), product.getSubcategory())) {
            foundProduct.setSubcategory(product.getSubcategory());
        }

        if(product.getVariant() != null && product.getVariant().length() > 0 && !Objects.equals(foundProduct.getVariant(), product.getVariant())) {
            foundProduct.setVariant(product.getVariant());
        }

        if(product.getSize() != null && product.getSize().length() > 0 && !Objects.equals(foundProduct.getSize(), product.getSize())) {
            foundProduct.setSize(product.getSize());
        }

        if(product.getPrice() != null && product.getPrice() > 0 && !Objects.equals(foundProduct.getPrice(), product.getPrice())) {
            foundProduct.setPrice(product.getPrice());
        }
    }
}
