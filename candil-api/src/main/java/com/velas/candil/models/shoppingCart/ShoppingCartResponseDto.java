package com.velas.candil.models.shoppingCart;

import com.velas.candil.models.cartItem.CartItemResponseDto;

import java.util.List;

public record ShoppingCartResponseDto(
        Long id,
        Double subTotal,
        List<CartItemResponseDto> items
) {
}
