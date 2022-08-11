package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Product;
import org.academiadecodigo.bootcamp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private ProductRepository product;

    @Autowired
    public void setProduct(ProductRepository product) {
        this.product = product;
    }

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
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,
                               @Valid @RequestBody Product productDetails) {

        Product productfound = product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        /**
         * Review all get/set to ensure that all are here
         */
        productfound.setId(productId);
        productfound.setName(productDetails.getName());
        productfound.setDescription(productDetails.getDescription());
        productfound.setPrice(productDetails.getPrice());

        Product updatedProduct = product.save(productfound);
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
        Product product = this.product.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        this.product.delete(product);

        return ResponseEntity.ok().build();
    }
}
