package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Cart;
import org.academiadecodigo.bootcamp.repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartRepository cart;

    private ProductRepository product;

    private CustomerRepository customer;

    @Autowired
    public void setCart(CartRepository cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProduct(ProductRepository product) {
        this.product = product;
    }

    @Autowired
    public void setCustomer(CustomerRepository customer) {
        this.customer = customer;
    }

    @GetMapping("")
    public List<Cart> getAllCart() {
        return cart.findAll();
    }

    @PostMapping("")
    public Cart createCart(@Valid @RequestBody Cart cart) {

        cart.setCustomerCart(customer.findById(cart.getLoadCustomer())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", cart.getLoadCustomer())));

        cart.setProductCart(product.findById(cart.getLoadProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", cart.getLoadProduct())));

        return this.cart.save(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable(value = "id") Long cartId) {
        return cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable(value = "id") Long cartId,
                                    @Valid @RequestBody Cart cartDetails) {

        Cart cartfound = cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));

        /**
         * Review all get/set to ensure that all are here
         */
        cartfound.setId(cartId);
        cartfound.setQuantity(cartDetails.getQuantity());
        cartfound.setPrice(cartDetails.getPrice());

        cartfound.setCustomerCart(customer.findById(cartfound.getLoadCustomer())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", cartfound.getLoadCustomer())));

        cartfound.setProductCart(product.findById(cartfound.getLoadProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", cartfound.getLoadProduct())));

        return cart.save(cartfound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable(value = "id") Long cartId) {
        Cart cart = this.cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));

        this.cart.delete(cart);

        return ResponseEntity.ok().build();
    }
}
