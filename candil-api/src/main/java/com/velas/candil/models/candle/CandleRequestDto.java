package com.velas.candil.models.candle;

import com.velas.candil.models.ingredient.IngredientRequestDto;

import java.util.List;
import java.util.Set;

public record CandleRequestDto(
        String name,
        String description,
        String principalImage,
        Integer stock,
        Set<MaterialEnum> materialEnums,
        Set<FeatureEnum> featureEnums,
        Set<CategoryEnum> categories,
        List<String> images,
        List<IngredientRequestDto> ingredients
) {}