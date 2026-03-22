package com.velas.candil.services;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.cartItem.CartItem;
import com.velas.candil.entities.order.Order;
import com.velas.candil.entities.shoppingCart.ShoppingCart;
import com.velas.candil.entities.user.User;
import com.velas.candil.repositories.CandleRepository;
import com.velas.candil.repositories.ShoppingCartRepository;
import com.velas.candil.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartServiceImp implements ShoppingCartService{

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final CandleRepository candleRepository;

    @Override
    public ShoppingCart getOrCreateCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return shoppingCartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart();
                    cart.setUser(user);
                    return shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart getCartByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return shoppingCartRepository.findByUser(user)
                .orElseThrow(()->new RuntimeException("User not found")
                );
    }

    @Override
    public ShoppingCart addOneItem(Long userId, Long candleId) {

        ShoppingCart cart = getOrCreateCart(userId);

        CartItem item = cart.getCartItems().stream()
                .filter(x -> Objects.equals(x.getCandle().getId(), candleId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.increaseQuantity(1);
        } else {
            Candle candle = candleRepository.findById(candleId)
                    .orElseThrow(() -> new RuntimeException("Candle not found"));

            CartItem newItem = new CartItem();
            newItem.setCandle(candle);
            newItem.setQuantity(1);
            newItem.setShoppingCart(cart);

            cart.getCartItems().add(newItem);
        }

        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart removeOneItem(Long userId, Long candleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(x -> Objects.equals(x.getCandle().getId(), candleId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Shopping cart item not found"));

        if (cartItem.getQuantity() > 1) {
            cartItem.decreaseQuantity(1);
        } else {
            shoppingCart.getCartItems().remove(cartItem);
        }

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart removeItemCompletely(Long userId, Long candleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(x -> Objects.equals(x.getCandle().getId(), candleId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Shopping cart item not found"));

        shoppingCart.getCartItems().remove(cartItem);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

         shoppingCart.getCartItems().clear();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Order checkout(Long userId) {
        //TODO
        return null;
    }
}
