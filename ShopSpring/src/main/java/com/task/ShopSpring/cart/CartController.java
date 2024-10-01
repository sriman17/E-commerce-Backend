package com.task.ShopSpring.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        Cart updatedCart = cartService.addItemToCart(userId, cartItem);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeItemFromCart(userId, productId);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/{userId}/update/{productId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCart(userId, productId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/clear/{productId}")
    public ResponseEntity<Cart> clearCart(@PathVariable Long userId) {
        Cart clearedCart = cartService.clearCart(userId);
        return new ResponseEntity<>(clearedCart, HttpStatus.OK);
    }
}