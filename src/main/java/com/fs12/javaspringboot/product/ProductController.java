package com.fs12.javaspringboot.product;

import com.fs12.javaspringboot.util.ProductNotFoundException;
import com.fs12.javaspringboot.util.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("productId") int productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @PutMapping(path = "{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") int productId,
                              @RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.updateProduct(productId, product), HttpStatus.OK);
    }
}
