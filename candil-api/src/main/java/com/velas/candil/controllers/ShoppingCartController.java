package com.velas.candil.controllers;

import com.velas.candil.models.shoppingCart.ShoppingCartResponseDto;
import com.velas.candil.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
@Tag(name = "Shopping cart", description = "Operations related with shopping cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Operation(
            summary = "Get shopping cart",
            description = "Retrieves the shopping cart of the authenticated user. If the cart does not exist, it will be created."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId) {

        return ResponseEntity.ok(shoppingCartService.getOrCreateCart(userId));
    }

    @Operation(
            summary = "Increase item quantity",
            description = "Increases the quantity of a specific candle in the user's shopping cart by 1."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item quantity increased"),
            @ApiResponse(responseCode = "404", description = "Candle not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/items/{candleId}/increase")
    public ResponseEntity<ShoppingCartResponseDto> increaseItemQuantity(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId,

            @Parameter(description = "ID of the candle to increase quantity", example = "1")
            @PathVariable Long candleId) {

        return ResponseEntity.ok(shoppingCartService.increaseItemQuantity(userId, candleId));
    }

    @Operation(
            summary = "Decrease item quantity",
            description = "Decreases the quantity of a specific candle in the user's shopping cart by 1."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item quantity decreased"),
            @ApiResponse(responseCode = "404", description = "Item not found in cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/items/{candleId}/decrease")
    public ResponseEntity<ShoppingCartResponseDto> decreaseItemQuantity(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId,

            @Parameter(description = "ID of the candle to decrease quantity", example = "1")
            @PathVariable Long candleId) {

        return ResponseEntity.ok(shoppingCartService.decreaseItemQuantity(userId, candleId));
    }

    @Operation(
            summary = "Add item to cart",
            description = "Adds a new candle to the shopping cart. If the item already exists, its quantity may be increased."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added to cart"),
            @ApiResponse(responseCode = "404", description = "Candle not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/items/{candleId}")
    public ResponseEntity<ShoppingCartResponseDto> addItem(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId,

            @Parameter(description = "ID of the candle to add", example = "1")
            @PathVariable Long candleId) {

        return ResponseEntity.ok(shoppingCartService.addItem(userId, candleId));
    }

    @Operation(
            summary = "Remove item from cart",
            description = "Removes a specific candle completely from the shopping cart."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removed from cart"),
            @ApiResponse(responseCode = "404", description = "Item not found in cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/items/{candleId}")
    public ResponseEntity<ShoppingCartResponseDto> removeItem(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId,

            @Parameter(description = "ID of the candle to remove", example = "1")
            @PathVariable Long candleId) {

        return ResponseEntity.ok(shoppingCartService.removeItem(userId, candleId));
    }

    @Operation(
            summary = "Clear shopping cart",
            description = "Removes all items from the authenticated user's shopping cart."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart cleared successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/clear")
    public ResponseEntity<ShoppingCartResponseDto> clearCart(
            @Parameter(hidden = true)
            @AuthenticationPrincipal(expression = "id") Long userId) {

        return ResponseEntity.ok(shoppingCartService.clearCart(userId));
    }
}