package com.fs12.javaspringboot.product;

import com.fs12.javaspringboot.util.ProductNotFoundException;
import com.fs12.javaspringboot.util.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Product> getProducts() throws ProductsNotFoundException {
        List<Product> products = productRepository.findAll();

        if(!products.isEmpty()) {
            return products;
        } else {
            throw new ProductsNotFoundException("No products found.");
        }
    }

    public List<Product> getProductsWithSorting(String field) throws ProductsNotFoundException {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(!products.isEmpty()) {
            return products;
        } else {
            throw new ProductsNotFoundException("No products found.");
        }
    }

    public Page<Product> getProductsWithPagination(int offset, int pageSize) throws ProductsNotFoundException {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(offset, pageSize));

        if(!productsPage.isEmpty()) {
            return productsPage;
        } else {
            throw new ProductsNotFoundException("No products found.");
        }
    }

    public Page<Product> getProductsWithPaginationAndSorting(int offset, int pageSize, String field) throws ProductsNotFoundException {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));

        if(!productsPage.isEmpty()) {
            return productsPage;
        } else {
            throw new ProductsNotFoundException("No products found.");
        }
    }

    public Optional<Product> getProduct(int productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return product;
        } else {
            throw new ProductNotFoundException("Product with id " + productId + " does not exist.");
        }
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public String deleteProduct(int productId) throws ProductNotFoundException {
        boolean exists = productRepository.existsById(productId);

        if(!exists) {
            throw new ProductNotFoundException("Product with id " + productId + " does not exist.");
        }

        productRepository.deleteById(productId);

        return "Product with id " + productId + " deleted successfully.";
    }

    @Transactional
    public Product updateProduct(int productId, Product product) throws ProductNotFoundException {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " does not exist."));

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

        return foundProduct;
    }
}
