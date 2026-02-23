package com.velas.candil.services;

import com.velas.candil.entities.candle.Category;
import com.velas.candil.entities.candle.Feature;
import com.velas.candil.entities.candle.Material;
import com.velas.candil.models.candle.CandleRequestDto;
import com.velas.candil.models.candle.CandleResponseDto;
import com.velas.candil.models.candle.CandleUpdateDto;
import com.velas.candil.repositories.CandleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandleServiceImp implements CandleService {

    private final CandleRepository candleRepository;

    @Override
    public Page<CandleResponseDto> findByCategory(Pageable pageable, Category category) {
        return null;
    }

    @Override
    public Page<CandleResponseDto> findByMaterial(Pageable pageable, Material material) {
        return null;
    }

    @Override
    public Page<CandleResponseDto> findByFeature(Pageable pageable, Feature feature) {
        return null;
    }

    @Override
    public Page<CandleResponseDto> findByDate(Pageable pageable, Date date) {
        return null;
    }

    @Override
    public Page<CandleResponseDto> findByNameContaining(Pageable pageable, String name) {
        return null;
    }

    @Override
    public CandleResponseDto create(CandleRequestDto candleRequestDto) {
        return null;
    }

    @Override
    public Page<CandleResponseDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public CandleResponseDto findById(Long aLong) {
        return null;
    }

    @Override
    public CandleResponseDto update(CandleUpdateDto candleUpdateDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
