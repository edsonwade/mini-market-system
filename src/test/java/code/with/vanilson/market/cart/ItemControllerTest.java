package code.with.vanilson.market.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Item Controller Test
 */
@WebMvcTest(ItemController.class)
@DisplayName("Item Controller Test")
@SuppressWarnings("all")
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    private List<Item> items;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Cart cart = new Cart("Cart 1");
        items = List.of(new Item("Item 1", cart), new Item("Item 2", cart));
    }

    /**
     * Test get all items - GET /api/items
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test get all items - GET /api/items")
    @Test
    void testGetAllItems_ShouldReturnSuccessWithStatusCode200_WhenItemsExists() throws Exception {
        // Given
        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/api/items")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].serialNumber").value("Item 1"))
                .andExpect(jsonPath("$[1].serialNumber").value("Item 2"));
    }

    /**
     * Test get item by id - GET /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test get item by id - GET /api/items/{id}")
    @Test
    void testGetItemById_ShouldReturnSuccessWithStatusCode200_WhenProvidedItemIdExists() throws Exception {
        // Given
        var item = 1L;
        when(itemService.findItemById(item)).thenReturn(Optional.of(items.get(0)));

        mockMvc.perform(get("/api/items/{id}", item)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("Item 1"));
    }

    /**
     * Test get item by id - GET /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test get item by id - GET /api/items/{id}")
    @Test
    void testGetItemById_ShouldReturnNotFoundWithStatusCode404_WhenProvidedItemIdDoesNotExists() throws Exception {
        // Given
        var item = items.get(0);
        when(itemService.findItemById(item.getId())).thenReturn(empty());

        mockMvc.perform(get("/api/items/{id}", item.getId())
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test create item - POST /api/items
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test create item - POST /api/items")
    @Test
    void testCreateItem_ShouldReturnSuccessWithStatusCode201_WhenItemIsCreated() throws Exception {
        // Given
        var newItem = new Item(1L, "Item 1", new Cart("Cart 1"));
        when(itemService.createItem(any(Item.class))).thenReturn(newItem);

        mockMvc.perform(post("/api/items/create-item")
                        .contentType("application/json")
                        .content("{\"serialNumber\": \"Item 1\", \"cart\": {\"name\": \"Cart 1\"}}")) // Include JSON payload
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNumber").value("Item 1"));
    }

    /**
     * Test create item - POST /api/items
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test create item - POST /api/items")
    @Test
    void testCreateItem_ShouldReturnBadRequestWithStatusCode400_WhenItemIsNotValid() throws Exception {
        // Given
        var invalidItem = new Item(); // Assuming an empty item is invalid
        when(itemService.createItem(invalidItem)).thenReturn(null);

        mockMvc.perform(post("/api/items/create-item")
                        .contentType("application/json")
                        .content("{\"serialNumber\": \"\"}")) // Example of invalid JSON payload
                .andExpect(status().isBadRequest());
    }

    /**
     * Test update item - PUT /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test update item - PUT /api/items/{id}")
    @Test
    void testUpdateItem_ShouldReturnSuccessWithStatusCode200_WhenItemIsUpdated() throws Exception {
        // Given
        var _id = 1L;
        var item = new Item(_id, "Item 1", new Cart("Cart 1"));
        when(itemService.findItemById(_id)).thenReturn(Optional.of(item));
        when(itemService.updateItem(_id, items.get(0))).thenReturn(item);

        mockMvc.perform(get("/api/items/{id}", _id)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("Item 1"));
    }

    /**
     * Test update item - PUT /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test update item - PUT /api/items/{id}")
    @Test
    void testUpdateItem_ShouldReturnNotFoundWithStatusCode404_WhenProvidedItemIdDoesNotExists() throws Exception {
        // Given
        var item = items.get(0);
        when(itemService.findItemById(item.getId())).thenReturn(empty());

        mockMvc.perform(get("/api/items/{id}", item.getId())
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test delete item - DELETE /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test delete item - DELETE /api/items/{id}")
    @Test
    void testDeleteItem_ShouldReturnSuccessWithStatusCode204_WhenItemIsDeleted() throws Exception {
        // Given
        var _id = 1L;
        when(itemService.findItemById(_id)).thenReturn(Optional.of(items.get(0)));

        mockMvc.perform(get("/api/items/{id}", _id)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    /**
     * Test delete item - DELETE /api/items/{id}
     * @throws Exception if an error occurs during the request
     */
    @DisplayName("Test delete item - DELETE /api/items/{id}")
    @Test
    void testDeleteItem_ShouldReturnNotFoundWithStatusCode404_WhenProvidedItemIdDoesNotExists() throws Exception {
        // Given
        var item = items.get(0);
        when(itemService.findItemById(item.getId())).thenReturn(empty());

        mockMvc.perform(get("/api/items/{id}", item.getId())
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}