package com.velas.candil.controllers;

import com.velas.candil.entities.shoppingCart.ShoppingCart;
import com.velas.candil.entities.user.User;
import com.velas.candil.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/candles")
@Tag(name = "Shopping cart", description = "Operations related with shopping cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    private ResponseEntity<ShoppingCart> getShoppingCart(@AuthenticationPrincipal User userDetails) {
        ShoppingCart shoppingCart = shoppingCartService.getCartByUser(userDetails.getId());
        return ResponseEntity.ok(shoppingCart);
    }


}
