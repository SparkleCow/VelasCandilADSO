package com.velas.candil.mappers;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.ingredient.Ingredient;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import com.velas.candil.models.ingredient.IngredientRequestDto;
import com.velas.candil.models.ingredient.IngredientResponseDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CandleMapper {

    @Autowired
    protected IngredientMapper ingredientMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "mapDtosToIngredients")
    public abstract Candle toEntity(CandleRequestDto dto);

    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "mapIngredientsToResponse")
    public abstract CandleResponseDto toResponse(Candle candle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    public abstract void updateEntityFromDto(CandleUpdateDto dto, @MappingTarget Candle candle);

    @Named("mapDtosToIngredients")
    protected List<Ingredient> mapDtosToIngredients(List<IngredientRequestDto> dtos) {

        if (dtos == null) return null;

        return dtos.stream()
                .map(ingredientMapper::toEntity)
                .toList();
    }

    @Named("mapIngredientsToResponse")
    protected List<IngredientResponseDto> mapIngredientsToResponse(List<Ingredient> ingredients) {

        if (ingredients == null) return List.of();

        return ingredients.stream()
                .map(ingredientMapper::toResponse)
                .toList();
    }

    @AfterMapping
    protected void linkIngredients(@MappingTarget Candle candle) {

        if (candle.getIngredients() != null) {
            candle.getIngredients()
                    .forEach(i -> i.setCandle(candle));
        }
    }
}