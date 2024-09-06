package code.with.vanilson.market.cart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CartService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-06
 */
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Page<Cart> getCarts(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    public Optional<Cart> findCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return cart;
        }
        throw new ResourceNotFoundException("Cart not found with id " + id);

    }

    public Cart createCartWithItems(Cart cart) {
        // Save the cart first
        Cart savedCart = cartRepository.save(cart);

        // Assign the saved cart to each item and save them
        for (Item item : cart.getItems()) {
            item.setCart(savedCart); // Associate each item with the saved cart
            itemRepository.save(item); // Save each item
        }

        return savedCart;
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
