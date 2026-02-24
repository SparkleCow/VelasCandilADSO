package com.velas.candil.models.ingredient;

public enum IngredientsEnum {

    // WAXES (price per pound)
    BEESWAX(9000.0, IngredientType.WAX),
    SOY_WAX(12000.0, IngredientType.WAX),
    PARAFFIN_WAX(10000.0, IngredientType.WAX),
    COCONUT_WAX(14000.0, IngredientType.WAX),
    RAPSOY_WAX(13000.0, IngredientType.WAX),

    // ADDITIVES (price per pound)
    STEARIC_ACID(7000.0, IngredientType.ADDITIVES),
    VYBAR(8000.0, IngredientType.ADDITIVES),

    // FRAGRANCES (price per ounce)
    ESSENTIAL_OIL(7000.0, IngredientType.FRAGRANCE),
    FRAGRANCE_OIL(5000.0, IngredientType.FRAGRANCE),

    // COLORANTS (price per ounce)
    CANDLE_DYE(3500.0, IngredientType.COLORANT),
    MICA_POWDER(4500.0, IngredientType.COLORANT),

    // WICKS (price per unit)
    WICK_COTTON(300.0, IngredientType.WICK),
    WICK_WOOD(650.0, IngredientType.WICK),
    WICK_TAB(80.0, IngredientType.WICK),

    // CONTAINERS (price per unit)
    GLASS_JAR_SMALL(4500.0, IngredientType.CONTAINER),
    GLASS_JAR_MEDIUM(6500.0, IngredientType.CONTAINER),
    GLASS_JAR_LARGE(8000.0, IngredientType.CONTAINER),
    METAL_TIN(5500.0, IngredientType.CONTAINER),

    // PACKAGING (price per unit)
    LID_METAL(1500.0, IngredientType.PACKAGING),
    LABEL(700.0, IngredientType.PACKAGING),
    BRANDED_STICKER(1200.0, IngredientType.PACKAGING),
    PACKAGING_BOX(2000.0, IngredientType.PACKAGING),
    FILLER_PAPER(500.0, IngredientType.PACKAGING),
    PACKING_TAPE(300.0, IngredientType.PACKAGING);

    public final Double pricePerUnit;
    public final IngredientType type;

    IngredientsEnum(double pricePerUnit, IngredientType type) {
        this.pricePerUnit = pricePerUnit;
        this.type = type;
    }
}