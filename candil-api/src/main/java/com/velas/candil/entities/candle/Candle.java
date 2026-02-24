package com.velas.candil.entities.candle;

import com.velas.candil.entities.ingredient.Ingredient;
import com.velas.candil.models.candle.CategoryEnum;
import com.velas.candil.models.candle.FeatureEnum;
import com.velas.candil.models.candle.MaterialEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "candles")
// TODO - AUDITING
public class Candle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String principalImage;
    private Integer stock;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_materials", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "material")
    @Enumerated(EnumType.STRING)
    private Set<MaterialEnum> materialEnums = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_features", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feature")
    @Enumerated(EnumType.STRING)
    private Set<FeatureEnum> featureEnums = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Set<CategoryEnum> categories = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images = new ArrayList<>();

    @OneToMany(mappedBy = "candle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
    private Double price = 0.0;

    public void removeStock(Integer quantity){
        this.stock -= quantity;
    }

    public void addStock(Integer quantity){
        this.stock += quantity;
    }
}