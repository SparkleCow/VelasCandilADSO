package com.velas.candil.services;

import com.velas.candil.models.ingredient.IngredientRequestDto;
import com.velas.candil.models.ingredient.IngredientResponseDto;
import com.velas.candil.models.ingredient.IngredientType;
import com.velas.candil.models.ingredient.IngredientUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientService extends CrudService<IngredientRequestDto, IngredientResponseDto, IngredientUpdateDto, Long> {

    Page<IngredientResponseDto> findAll(Pageable pageable);
    Page<IngredientResponseDto> findByNameContaining(String name, Pageable pageable);
    Page<IngredientResponseDto> findByIngredientType(IngredientType ingredientType, Pageable pageable);
}
