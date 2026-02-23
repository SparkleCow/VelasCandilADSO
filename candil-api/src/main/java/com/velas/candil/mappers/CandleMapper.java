package com.velas.candil.mappers;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.ingredient.Ingredient;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import com.velas.candil.repositories.IngredientRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public abstract class CandleMapper {

    @Autowired
    protected IngredientRepository ingredientRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", source = "ingredientIds", qualifiedByName = "mapIdsToIngredients")
    public abstract Candle toEntity(CandleRequestDto dto);

    @Mapping(target = "ingredientIds", source = "ingredients", qualifiedByName = "mapIngredientsToIds")
    public abstract CandleResponseDto toResponse(Candle candle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", source = "ingredientIds", qualifiedByName = "mapIdsToIngredients")
    public abstract void updateEntityFromDto(CandleUpdateDto dto, @MappingTarget Candle candle);

    @Named("mapIdsToIngredients")
    protected List<Ingredient> mapIdsToIngredients(List<Long> ids) {
        if (ids == null) return null;
        return ingredientRepository.findAllById(ids);
    }

    @Named("mapIngredientsToIds")
    protected List<Long> mapIngredientsToIds(List<Ingredient> ingredients) {
        if (ingredients == null) return List.of();
        return ingredients.stream()
                .map(Ingredient::getId)
                .toList();
    }
}