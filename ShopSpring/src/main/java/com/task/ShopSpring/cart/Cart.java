package com.task.ShopSpring.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems = new ArrayList<>();

    private BigDecimal totalPrice;

    public Cart() {
        this.totalPrice = BigDecimal.ZERO;
    }

    public Cart(Long userId) {
        this.userId = userId;
        this.totalPrice = BigDecimal.ZERO;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addItem(CartItem item) {
        this.cartItems.add(item);
        this.totalPrice = this.totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
    }

    public void removeItem(CartItem item) {
        this.cartItems.remove(item);
        this.totalPrice = this.totalPrice.subtract(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
    }

    public void clearCart() {
        this.cartItems.clear();
        this.totalPrice = BigDecimal.ZERO;
    }
}
