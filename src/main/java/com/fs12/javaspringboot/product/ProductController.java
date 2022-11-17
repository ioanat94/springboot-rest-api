package com.fs12.javaspringboot.product;

import com.fs12.javaspringboot.util.ProductNotFoundException;
import com.fs12.javaspringboot.util.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(path = "/sort/{field}")
    public ResponseEntity<List<Product>> getProductsWithSorting(@PathVariable String field) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsWithSorting(field));
    }

    @GetMapping(path = "/page/{offset}/size/{pageSize}")
    public ResponseEntity<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsWithPagination(offset, pageSize));
    }

    @GetMapping(path = "/sort/{field}/page/{offset}/size/{pageSize}")
    public ResponseEntity<Page<Product>> getProductsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsWithPaginationAndSorting(offset, pageSize, field));
    }

    @GetMapping(path = "/filter/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsByName(name));
    }

    @GetMapping(path = "/filter/pet/{pet}")
    public ResponseEntity<List<Product>> getProductsByPet(@PathVariable String pet) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsByPet(pet));
    }

    @GetMapping(path = "/filter/subcategory/{subcategory}")
    public ResponseEntity<List<Product>> getProductsBySubcategory(@PathVariable String subcategory) throws ProductsNotFoundException {
        return ResponseEntity.ok(productService.getProductsBySubcategory(subcategory));
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
