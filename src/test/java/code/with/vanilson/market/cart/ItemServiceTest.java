package code.with.vanilson.market.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Optional.of;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Item Service Test")
class ItemServiceTest {

    private final ItemRepository itemRepository = mock(ItemRepository.class);
    private final CartRepository cartRepository = mock(CartRepository.class);
    private ItemService itemService;
    private Cart cart;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        cart = new Cart("Cart 1");
        items = List.of(new Item("Item 1", cart), new Item("Item 2", cart));
        itemService = new ItemService(itemRepository, cartRepository);

    }

    // Add tests here
    @DisplayName("Test get all items")
    @Test
    void testGetAllItems_ShouldReturnAllItems_WhenItemsExists() {
        // Given

        when(itemRepository.findAll()).thenReturn(items);

        // When
        var result = itemService.getAllItems();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isSameAs(items);
        assertEquals(result, items, "Items should be equal");
        assertEquals(result.size(), items.size(), "Items size should be equal");
        assertEquals(result.get(0).getCart(), items.get(0).getCart(), "Item names should be equal");

        verify(itemRepository, times(1)).findAll();

    }

    @DisplayName("Test get item by id")
    @Test
    void testGetItemById_ShouldReturnItem_WhenProvidedItemIdExists() {
        // Given
        var item = items.get(0);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));

        // When
        var result = itemService.findItemById(item.getId());

        // Then
        assertThat(result).isNotNull();
        assertTrue(result.isPresent(), "Item should be present");
        assertEquals(item.getCart(), result.get().getCart(), "Item names should be equal");
        verify(itemRepository, times(1)).findById(item.getId());
    }

    @DisplayName("Test get item by id throws exception")
    @Test
    void testGetItemById_ShouldThrowsNotFoundException_WhenProvidedItemDoesNotExist() {
        // Given
        var item = items.get(0);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));

        // When
        var messgae = assertThrows(ResourceNotFoundException.class, () -> itemService.findItemById(2L));

        // Then
        assertThat(messgae).isNotNull();
        assertTrue(messgae.getMessage().contains("Item not found with id 2"));

        verify(itemRepository, times(1)).findById(2L);
    }

    @DisplayName("Test create item")
    @Test
    void testCreateItem_ShouldReturnCreatedItem_WhenItemIsCreated() {
        // Given
        var item = items.get(0);
        when(cartRepository.findById(item.getCart().getId())).thenReturn(of(cart));
        when(itemRepository.save(item)).thenReturn(item);

        // When
        var result = itemService.createItem(item);

        // Then
        assertThat(result).isNotNull();
        assertEquals(item.getCart(), result.getCart(), "Item names should be equal");
        verify(cartRepository, times(1)).findById(item.getCart().getId());
        verify(itemRepository, times(1)).save(item);
    }

    @DisplayName("Test create item throws exception")
    @Test
    void testCreateItem_ShouldThrowsNotFoundException_WhenProvidedCartDoesNotExist() {
        // Given
        var item = items.get(0);
        when(cartRepository.findById(anyLong())).thenReturn(of(cart));
        when(itemRepository.save(item)).thenReturn(item);

        // When
        var messgae = assertThrows(ResourceNotFoundException.class,
                () -> itemService.createItem(new Item("Item 3", new Cart("Cart 2"))));

        // Then
        assertThat(messgae).isNotNull();
        assertTrue(messgae.getMessage().contains("Cart not found with id null"));

        verify(cartRepository, times(1)).findById(null);
    }

    @DisplayName("Test update item")
    @Test
    void testUpdateItem_ShouldReturnUpdatedItem_WhenProvidedItemExists() {
        // Given
        var item = items.get(0);
        var updatedItem = new Item("Item 3", cart);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));
        when(itemRepository.save(item)).thenReturn(item);

        // When
        var result = itemService.updateItem(item.getId(), updatedItem);

        // Then
        assertThat(result).isNotNull();
        assertEquals(updatedItem.getSerialNumber(), result.getSerialNumber(), "Item names should be equal");
        verify(itemRepository, times(1)).findById(item.getId());
        verify(itemRepository, times(1)).save(item);
    }

    @DisplayName("Test update item throws exception")
    @Test
    void testUpdateItem_ShouldThrowsNotFoundException_WhenProvidedItemDoesNotExist() {
        // Given
        var item = items.get(0);
        var updatedItem = new Item("Item 3", cart);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));
        when(itemRepository.save(item)).thenReturn(item);

        // When
        var messgae = assertThrows(ResourceNotFoundException.class, () -> itemService.updateItem(2L, updatedItem));

        // Then
        assertThat(messgae).isNotNull();
        assertTrue(messgae.getMessage().contains("Item not found with id 2"));

        verify(itemRepository, times(1)).findById(2L);
    }

    @DisplayName("Test delete item")
    @Test
    void testDeleteItem_ShouldDeleteItem_WhenProvidedItemIdExists() {
        // Given
        var item = items.get(0);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));

        // When
        itemService.deleteItem(item.getId());

        // Then
        verify(itemRepository, times(1)).deleteById(item.getId());
        verify(itemRepository, times(1)).findById(item.getId());
    }

    @DisplayName("Test delete item throws exception")
    @Test
    void testDeleteItem_ShouldThrowsNotFoundException_WhenProvidedItemDoesNotExist() {
        // Given
        var item = items.get(0);
        when(itemRepository.findById(item.getId())).thenReturn(of(item));

        // When
        var messgae = assertThrows(ResourceNotFoundException.class, () -> itemService.deleteItem(2L));

        // Then
        assertThat(messgae).isNotNull();
        assertTrue(messgae.getMessage().contains("Item not found with id 2"));

        verify(itemRepository, times(1)).findById(2L);
    }

}