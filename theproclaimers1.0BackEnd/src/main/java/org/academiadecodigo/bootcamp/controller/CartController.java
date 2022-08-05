package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Cart;
import org.academiadecodigo.bootcamp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartRepository cart;

    @GetMapping("")
    public List<Cart> getAllCart() {
        return cart.findAll();
    }

    @PostMapping("")
    public Cart createCart(@Valid @RequestBody Cart cart) {
        return this.cart.save(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable(value = "id") Long cartId) {
        return cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", cartId));
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable(value = "id") Long cartId,
                                    @Valid @RequestBody Cart cartDetails) {

        Cart cartfound = cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", cartId));

        /**
         * Review all get/set to ensure that all are here
         */
        cartfound.setId(cartId);
        cartfound.setCustomer(cartDetails.getCustomer());
        cartfound.setProduct(cartDetails.getProduct());
        cartfound.setQuantity(cartDetails.getQuantity());
        cartfound.setPrice(cartDetails.getPrice());

        Cart updatedCart = cart.save(cartfound);
        return updatedCart;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable(value = "id") Long cartId) {
        Cart cart = this.cart.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", cartId));

        this.cart.delete(cart);

        return ResponseEntity.ok().build();
    }
}
