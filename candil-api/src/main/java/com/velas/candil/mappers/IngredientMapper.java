package com.velas.candil.mappers;

import com.velas.candil.entities.ingredient.Ingredient;
import com.velas.candil.models.ingredient.IngredientRequestDto;
import com.velas.candil.models.ingredient.IngredientResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pricePerUnit", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "ingredientType", ignore = true)
    @Mapping(target = "candle", ignore = true)
    Ingredient toEntity(IngredientRequestDto dto);

    IngredientResponseDto toResponse(Ingredient ingredient);
}