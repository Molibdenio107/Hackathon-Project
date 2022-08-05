package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Product;
import org.academiadecodigo.bootcamp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository product;

    @GetMapping("")
    public List<Product> getAllProduct() {
        return product.findAll();
    }

    @PostMapping("")
    public Product createProduct(@Valid @RequestBody Product product) {
        return this.product.save(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", productId));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,
                               @Valid @RequestBody Product cartDetails) {

        Product productfound = product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", productId));

        /**
         * Review all get/set to ensure that all are here
         */
        productfound.setId(productId);
        productfound.setPrice(cartDetails.getPrice());

        Product updatedProduct = product.save(productfound);
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
        Product product = this.product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", productId));

        this.product.delete(product);

        return ResponseEntity.ok().build();
    }
}
