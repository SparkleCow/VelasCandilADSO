package com.velas.candil.mappers;

import com.velas.candil.entities.cartItem.CartItem;
import com.velas.candil.models.cartItem.CartItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "candle.id", target = "candleId")
    @Mapping(source = "candle.name", target = "candleName")
    @Mapping(source = "priceSnapshot", target = "price")
    CartItemResponseDto toDto(CartItem item);
}