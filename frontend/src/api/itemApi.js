import axios from 'axios';

// Create an instance of axios with a base URL
const api = axios.create({
  baseURL: 'http://localhost:8081/api', // Update this URL to match your backend's base URL
});

export const createItem = async (item) => {
  try {
    const response = await api.post('/items', item);
    return response.data;
  } catch (error) {
    console.error('Error creating item:', error.response ? error.response.data : error.message);
    throw error;
  }
};

export const deleteItem = async (id) => {
  try {
    await api.delete(`/items/${id}`);
  } catch (error) {
    console.error(`Error deleting item with ID ${id}:`, error.response ? error.response.data : error.message);
    throw error;
  }
};
