package com.velas.candil.models.ingredient;

public record IngredientUpdateDto(
        IngredientsEnum name,
        Double amount){
}