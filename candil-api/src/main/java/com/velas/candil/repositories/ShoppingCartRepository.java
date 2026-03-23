package com.velas.candil.repositories;

import com.velas.candil.entities.shoppingCart.ShoppingCart;
import com.velas.candil.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUser(User user);
}
