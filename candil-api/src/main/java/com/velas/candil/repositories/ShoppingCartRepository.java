package com.velas.candil.repositories;

import com.velas.candil.entities.shoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
