package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.CartItemDTO;
import MatteoOrlando.CapStone.entities.CartItem;
import MatteoOrlando.CapStone.entities.User;
import MatteoOrlando.CapStone.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem addToCart(@AuthenticationPrincipal User user, @RequestBody CartItemDTO cartItemDTO) {
        return cartService.addToCart(user, cartItemDTO);
    }

    @GetMapping
    public List<CartItem> getCartItems(@AuthenticationPrincipal User user) {
        return cartService.getCartItems(user);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromCart(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        try {
            cartService.removeFromCart(user, productId);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found", e);
        }
    }
}
