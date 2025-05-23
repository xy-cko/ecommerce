package com.company.ecommerce.service;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderItemsQuery {
    @Autowired
    private DataManager dataManager;

    static private UUID cartId;

    static public void setCartId(UUID passedCartId) {
        cartId = passedCartId;
    }
    static public UUID getCartId() {
        return cartId;
    }
}