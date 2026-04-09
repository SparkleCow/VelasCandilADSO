package com.velas.candil.services;

import com.velas.candil.entities.order.Order;
import com.velas.candil.models.shoppingCart.ShoppingCartResponseDto;

public interface ShoppingCartService {

    ShoppingCartResponseDto getOrCreateCart(Long userId);

    ShoppingCartResponseDto getCart(Long userId);

    ShoppingCartResponseDto addItem(Long userId, Long candleId);

    ShoppingCartResponseDto removeItem(Long userId, Long candleId);

    ShoppingCartResponseDto increaseItemQuantity(Long userId, Long candleId);

    ShoppingCartResponseDto decreaseItemQuantity(Long userId, Long candleId);

    ShoppingCartResponseDto clearCart(Long userId);

    Order checkout(Long userId);
}