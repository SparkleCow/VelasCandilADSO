package com.velas.candil.models.cartItem;

import java.math.BigDecimal;

public record CartItemResponseDto(
        Long id,
        Long candleId,
        String candleName,
        BigDecimal price,
        Integer quantity,
        BigDecimal subtotal
) {
}
