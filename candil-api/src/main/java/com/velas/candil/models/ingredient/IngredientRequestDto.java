package com.velas.candil.models.ingredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IngredientRequestDto(
        @NotNull
        IngredientsEnum name,

        @NotNull
        @Positive
        Double amount
) {}