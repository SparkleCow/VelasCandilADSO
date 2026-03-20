package com.velas.candil.services;

import com.velas.candil.entities.order.Order;
import com.velas.candil.entities.shoppingCart.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getOrCreateCart(Long userId);

    ShoppingCart getCartByUser(Long userId);

    ShoppingCart addOneItem(Long userId, Long candleId);

    ShoppingCart removeOneItem(Long userId, Long candleId);

    ShoppingCart removeItemCompletely(Long userId, Long candleId);

    ShoppingCart clearCart(Long userId);

    Order checkout(Long userId);
}