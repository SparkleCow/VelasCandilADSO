package com.velas.candil.models.candle;

import com.velas.candil.models.ingredient.IngredientResponseDto;

import java.util.List;
import java.util.Set;

public record CandleResponseDto(
        Long id,
        String name,
        String description,
        String principalImage,
        Integer stock,
        Double price,
        Set<MaterialEnum> materialEnums,
        Set<FeatureEnum> featureEnums,
        Set<CategoryEnum> categories,
        List<String> images,
        List<IngredientResponseDto> ingredients
) {}