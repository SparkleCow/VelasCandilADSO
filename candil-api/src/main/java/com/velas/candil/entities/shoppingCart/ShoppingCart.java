package com.velas.candil.entities.shoppingCart;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.cartItem.CartItem;
import com.velas.candil.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "shopping_cart")
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setShoppingCart(this);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setShoppingCart(null);
    }
}
