package com.velas.candil.models.ingredient;

public enum IngredientsEnum {

    // WAXES (price per pound)
    BEESWAX(9000.0),
    SOY_WAX(12000.0),
    PARAFFIN_WAX(10000.0),
    COCONUT_WAX(14000.0),
    RAPSOY_WAX(13000.0),

    // ADDITIVES (price per pound)
    STEARIC_ACID(7000.0),
    VYBAR(8000.0),

    // FRAGRANCES (price per ounce)
    ESSENTIAL_OIL(7000.0),
    FRAGRANCE_OIL(5000.0),

    // COLORANTS (price per ounce)
    CANDLE_DYE(3500.0),
    MICA_POWDER(4500.0),

    // WICKS (price per unit)
    WICK_COTTON(300.0),
    WICK_WOOD(650.0),
    WICK_TAB(80.0),

    // CONTAINERS (price per unit)
    GLASS_JAR_SMALL(4500.0),
    GLASS_JAR_MEDIUM(6500.0),
    GLASS_JAR_LARGE(8000.0),
    METAL_TIN(5500.0),

    // PACKAGING (price per unit)
    LID_METAL(1500.0),
    LABEL(700.0),
    BRANDED_STICKER(1200.0),
    PACKAGING_BOX(2000.0),
    FILLER_PAPER(500.0),
    PACKING_TAPE(300.0);

    public final Double pricePerUnit;

    IngredientsEnum(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}