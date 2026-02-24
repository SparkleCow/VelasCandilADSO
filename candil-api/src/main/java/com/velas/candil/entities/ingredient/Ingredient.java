package com.velas.candil.entities.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.velas.candil.entities.candle.Candle;
import com.velas.candil.models.ingredient.IngredientType;
import com.velas.candil.models.ingredient.IngredientsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ingredients")
/*TODO - auditing*/
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IngredientsEnum name;
    private Double amount;
    private Double pricePerUnit;
    private Double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "candle_id")
    private Candle candle;
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public Ingredient(IngredientsEnum name, Double amount){
        this.name = name;
        this.amount = amount;
        this.ingredientType = name.type;
        this.pricePerUnit = name.pricePerUnit;
        this.price = calculatePrice();
    }

    public Double calculatePrice(){
        return amount * pricePerUnit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name=" + name +
                ", amount=" + amount +
                ", pricePerUnit=" + pricePerUnit +
                ", price=" + price +
                '}';
    }
}