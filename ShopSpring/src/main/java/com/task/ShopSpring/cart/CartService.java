package com.task.ShopSpring.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Retrieve the cart by user ID
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findAll().stream()
            .filter(cart -> cart.getUserId().equals(userId))
            .findFirst()
            .orElse(new Cart(userId)); // Return a new cart if none found
    }

    // Add an item to the cart
    public Cart addItemToCart(Long userId, CartItem cartItem) {
        Cart cart = getCartByUserId(userId);
        cart.addItem(cartItem);
        cartRepository.save(cart);
        return cart;
    }

    // Remove an item from the cart
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().removeIf(item -> item.getId().equals(productId));
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);
        return cart;
    }

    // Update the quantity of an item in the cart
    public Cart updateCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        for (CartItem item : cart.getCartItems()) {
            if (item.getId().equals(productId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);
        return cart;
    }

    // Clear the cart
    public Cart clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.clearCart();
        cartRepository.save(cart);
        return cart;
    }

    // Helper method to calculate the total price
    private BigDecimal calculateTotalPrice(Cart cart) {
        return cart.getCartItems().stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
