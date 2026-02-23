package com.velas.candil.services;

import com.velas.candil.models.candle.CategoryEnum;
import com.velas.candil.models.candle.FeatureEnum;
import com.velas.candil.models.candle.MaterialEnum;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface CandleService extends CrudService<CandleRequestDto, CandleResponseDto, CandleUpdateDto, Long> {

    Page<CandleResponseDto> findByCategory(Pageable pageable, CategoryEnum categoryEnum);
    Page<CandleResponseDto> findByMaterial(Pageable pageable, MaterialEnum materialEnum);
    Page<CandleResponseDto> findByFeature(Pageable pageable, FeatureEnum featureEnum);
    Page<CandleResponseDto> findByDate(Pageable pageable, Date date);
    Page<CandleResponseDto> findByNameContaining(Pageable pageable, String name);
}
