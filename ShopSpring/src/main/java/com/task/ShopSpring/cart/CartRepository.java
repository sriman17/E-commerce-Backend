package com.task.ShopSpring.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // You can define custom query methods here if needed
}
