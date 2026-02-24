package com.velas.candil.repositories;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.models.candle.CategoryEnum;
import com.velas.candil.models.candle.FeatureEnum;
import com.velas.candil.models.candle.MaterialEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {

    boolean existsByName(String name);

    Page<Candle> findByCategories(CategoryEnum categoryEnum, Pageable pageable);

    Page<Candle> findByMaterialEnums(MaterialEnum materialEnum, Pageable pageable);

    Page<Candle> findByFeatureEnums(FeatureEnum featureEnum, Pageable pageable);

    Page<Candle> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Page<Candle> findByCreatedAt(Date date, Pageable pageable);
}
