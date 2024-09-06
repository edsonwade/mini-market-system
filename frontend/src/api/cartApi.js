import axios from 'axios';

// Create an instance of axios with a base URL
const api = axios.create({
  baseURL: 'http://localhost:8081/api', // Update this URL to match your backend's base URL
});

export const getCarts = async (page) => {
    try {
      const response = await api.get(`/carts?page=${page}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching carts:', error.response ? error.response.data : error.message);
      throw error;
    }
  };
  

export const getCartById = async (id) => {
  try {
    const response = await api.get(`/carts/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching cart with ID ${id}:`, error.response ? error.response.data : error.message);
    throw error;
  }
};

export const createCart = async (cart) => {
  try {
    const response = await api.post('/carts', cart);
    return response.data;
  } catch (error) {
    console.error('Error creating cart:', error.response ? error.response.data : error.message);
    throw error;
  }
};

export const deleteCart = async (id) => {
  try {
    await api.delete(`/carts/${id}`);
  } catch (error) {
    console.error(`Error deleting cart with ID ${id}:`, error.response ? error.response.data : error.message);
    throw error;
  }
};
