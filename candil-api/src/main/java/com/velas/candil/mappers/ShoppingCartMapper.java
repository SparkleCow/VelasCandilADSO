package com.velas.candil.mappers;

import com.velas.candil.entities.shoppingCart.ShoppingCart;
import com.velas.candil.models.shoppingCart.ShoppingCartResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(source = "cartItems", target = "items")
    ShoppingCartResponseDto toDto(ShoppingCart cart);
}