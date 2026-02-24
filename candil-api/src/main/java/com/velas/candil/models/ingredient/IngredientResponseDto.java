package com.velas.candil.models.ingredient;

public record IngredientResponseDto(IngredientsEnum name,
                                   Double amount,
                                   Double price){
}