package code.with.vanilson.market.cart;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CartController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-06
 */
@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "create-cart")
    public ResponseEntity<Cart> createCart(@RequestBody @Valid Cart cart) {
        // Handle the creation of the cart along with its items
        Cart createdCart = cartService.createCartWithItems(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartService.findCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @DeleteMapping("/delete-cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
