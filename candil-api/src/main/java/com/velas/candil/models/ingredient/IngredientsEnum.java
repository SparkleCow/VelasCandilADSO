package com.velas.candil.models.ingredient;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum IngredientsEnum {

    // WAXES (price per pound)
    BEESWAX(new BigDecimal("9000.0"), IngredientType.WAX),
    SOY_WAX(new BigDecimal("12000.0"), IngredientType.WAX),
    PARAFFIN_WAX(new BigDecimal("10000.0"), IngredientType.WAX),
    COCONUT_WAX(new BigDecimal("14000.0"), IngredientType.WAX),
    RAPSOY_WAX(new BigDecimal("13000.0"), IngredientType.WAX),

    // ADDITIVES (price per pound)
    STEARIC_ACID(new BigDecimal("7000.0"), IngredientType.ADDITIVES),
    VYBAR(new BigDecimal("8000.0"), IngredientType.ADDITIVES),

    // FRAGRANCES (price per ounce)
    ESSENTIAL_OIL(new BigDecimal("7000.0"), IngredientType.FRAGRANCE),
    FRAGRANCE_OIL(new BigDecimal("5000.0"), IngredientType.FRAGRANCE),

    // COLORANTS (price per ounce)
    CANDLE_DYE(new BigDecimal("3500.0"), IngredientType.COLORANT),
    MICA_POWDER(new BigDecimal("4500.0"), IngredientType.COLORANT),

    // WICKS (price per unit)
    WICK_COTTON(new BigDecimal("300.0"), IngredientType.WICK),
    WICK_WOOD(new BigDecimal("650.0"), IngredientType.WICK),
    WICK_TAB(new BigDecimal("80.0"), IngredientType.WICK),

    // CONTAINERS (price per unit)
    GLASS_JAR_SMALL(new BigDecimal("4500.0"), IngredientType.CONTAINER),
    GLASS_JAR_MEDIUM(new BigDecimal("6500.0"), IngredientType.CONTAINER),
    GLASS_JAR_LARGE(new BigDecimal("8000.0"), IngredientType.CONTAINER),
    METAL_TIN(new BigDecimal("5500.0"), IngredientType.CONTAINER),

    // PACKAGING (price per unit)
    LID_METAL(new BigDecimal("1500.0"), IngredientType.PACKAGING),
    LABEL(new BigDecimal("700.0"), IngredientType.PACKAGING),
    BRANDED_STICKER(new BigDecimal("1200.0"), IngredientType.PACKAGING),
    PACKAGING_BOX(new BigDecimal("2000.0"), IngredientType.PACKAGING),
    FILLER_PAPER(new BigDecimal("500.0"), IngredientType.PACKAGING),
    PACKING_TAPE(new BigDecimal("300.0"), IngredientType.PACKAGING);

    private final BigDecimal pricePerUnit;
    private final IngredientType type;

    IngredientsEnum(BigDecimal pricePerUnit, IngredientType type) {
        this.pricePerUnit = pricePerUnit;
        this.type = type;
    }

}