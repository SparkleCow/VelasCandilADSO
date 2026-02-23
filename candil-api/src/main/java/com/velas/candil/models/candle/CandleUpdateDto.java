package com.velas.candil.models.candle;

import java.util.List;
import java.util.Set;

public record CandleUpdateDto(
        String name,
        String description,
        String principalImage,
        Integer stock,
        Set<MaterialEnum> materialEnums,
        Set<FeatureEnum> featureEnums,
        Set<CategoryEnum> categories,
        List<String> images,
        List<Long> ingredientIds
) {}