package com.velas.candil.services;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.ingredient.Ingredient;
import com.velas.candil.mappers.CandleMapper;
import com.velas.candil.models.candle.CategoryEnum;
import com.velas.candil.models.candle.FeatureEnum;
import com.velas.candil.models.candle.MaterialEnum;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import com.velas.candil.repositories.CandleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandleServiceImp implements CandleService {

    private final CandleRepository candleRepository;
    private final CandleMapper candleMapper;

    @Value("${candle.margin.multiplier:1.7}")
    private double marginMultiplier;

    @Override
    public Page<CandleResponseDto> findByCategory(Pageable pageable, CategoryEnum categoryEnum) {
        log.info("Fetching candles by category: {}", categoryEnum);
        return candleRepository.findByCategories(categoryEnum, pageable)
                .map(candleMapper::toResponse);
    }

    @Override
    public Page<CandleResponseDto> findByMaterial(Pageable pageable, MaterialEnum materialEnum) {
        log.info("Fetching candles by material: {}", materialEnum);
        return candleRepository.findByMaterialEnums(materialEnum, pageable)
                .map(candleMapper::toResponse);
    }

    @Override
    public Page<CandleResponseDto> findByFeature(Pageable pageable, FeatureEnum featureEnum) {
        log.info("Fetching candles by feature: {}", featureEnum);
        return candleRepository.findByFeatureEnums(featureEnum, pageable)
                .map(candleMapper::toResponse);
    }

    @Override
    public Page<CandleResponseDto> findByDate(Pageable pageable, Date date) {
//        log.info("Fetching candles by creation date: {}", date);
//        return candleRepository.findByCreatedAt(date, pageable)
//                .map(candleMapper::toResponse);
        return null;
    }

    @Override
    public Page<CandleResponseDto> findByNameContaining(Pageable pageable, String name) {
        log.info("Searching candles containing name: {}", name);
        return candleRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(candleMapper::toResponse);
    }

    @Override
    public CandleResponseDto create(CandleRequestDto candleRequestDto) {

        if (candleRepository.existsByName(candleRequestDto.name())) {
            log.warn("Attempt to create candle with existing name: {}", candleRequestDto.name());
            throw new IllegalArgumentException("Candle with this name already exists");
        }

        Candle candle = candleMapper.toEntity(candleRequestDto);

        double cost = candle.getIngredients()
                .stream()
                .mapToDouble(Ingredient::calculatePrice)
                .sum();

        candle.setPrice(cost * marginMultiplier);

        Candle saved = candleRepository.save(candle);

        log.info("Candle created successfully with id: {}", saved.getId());

        return candleMapper.toResponse(saved);
    }

    @Override
    public Page<CandleResponseDto> findAll(Pageable pageable) {
        log.info("Fetching all candles - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        return candleRepository.findAll(pageable)
                .map(candleMapper::toResponse);
    }

    @Override
    public CandleResponseDto findById(Long id) {
        log.info("Fetching candle by id: {}", id);

        Candle candle = candleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Candle not found with id: {}", id);
                    return new IllegalArgumentException("Candle not found");
                });

        return candleMapper.toResponse(candle);
    }

    @Override
    public CandleResponseDto update(CandleUpdateDto dto, Long id) {

        log.info("Updating candle with id: {}", id);

        Candle candle = candleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Candle not found for update with id: {}", id);
                    return new IllegalArgumentException("Candle not found");
                });

        candleMapper.updateEntityFromDto(dto, candle);

        double cost = candle.getIngredients()
                .stream()
                .mapToDouble(Ingredient::calculatePrice)
                .sum();

        candle.setPrice(cost * marginMultiplier);

        Candle updated = candleRepository.save(candle);

        log.info("Candle updated successfully with id: {}", updated.getId());

        return candleMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        log.info("Deleting candle with id: {}", id);

        if (!candleRepository.existsById(id)) {
            log.error("Attempt to delete non-existing candle with id: {}", id);
            throw new IllegalArgumentException("Candle not found");
        }

        candleRepository.deleteById(id);

        log.info("Candle deleted successfully with id: {}", id);
    }
}