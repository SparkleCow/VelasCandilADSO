package com.velas.candil.entities.cartItem;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.shoppingCart.ShoppingCart;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "candle_id")
    private Candle candle;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceSnapshot;

    @Column(nullable = false)
    private BigDecimal subtotal;

    public void increaseQuantity(int amount) {
        this.quantity += amount;
        recalculateSubtotal();
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
        recalculateSubtotal();
    }

    public void recalculateSubtotal() {
        this.subtotal = priceSnapshot.multiply(BigDecimal.valueOf(quantity));
    }
}
