package com.velas.candil.services;

import com.velas.candil.entities.candle.Category;
import com.velas.candil.entities.candle.Feature;
import com.velas.candil.entities.candle.Material;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface CandleService extends CrudService<CandleRequestDto, CandleResponseDto, CandleUpdateDto, Long> {

    Page<CandleResponseDto> findByCategory(Pageable pageable, Category category);
    Page<CandleResponseDto> findByMaterial(Pageable pageable, Material material);
    Page<CandleResponseDto> findByFeature(Pageable pageable, Feature feature);
    Page<CandleResponseDto> findByDate(Pageable pageable, Date date);
    Page<CandleResponseDto> findByNameContaining(Pageable pageable, String name);
}
