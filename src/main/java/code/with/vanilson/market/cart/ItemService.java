package code.with.vanilson.market.cart;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ItemService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-06
 */
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public ItemService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item;
        }
        throw new ResourceNotFoundException("Item not found with id " + id);
    }

    public Item createItem(Item item) {
        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + item.getCart().getId()));
        item.setCart(cart);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + id));
        existingItem.setSerialNumber(updatedItem.getSerialNumber());
        existingItem.setCart(updatedItem.getCart());
        return itemRepository.save(existingItem);
    }
}
