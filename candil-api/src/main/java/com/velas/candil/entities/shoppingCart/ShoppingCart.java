package com.velas.candil.entities.shoppingCart;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "shopping_cart")
@Builder
public class ShoppingCart {
}
