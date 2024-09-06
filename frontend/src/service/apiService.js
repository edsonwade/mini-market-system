// src/services/apiService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const handleError = (error) => {
  console.error('API call failed. ' + error);
  throw error;
};

export const getAllCarts = async () => {
  try {
    const response = await axios.get(`${API_URL}/carts`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const getCartById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/carts/${id}`);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const createCart = async (cart) => {
  try {
    const response = await axios.post(`${API_URL}/carts/create-cart`, cart);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const updateCart = async (id, cart) => {
  try {
    const response = await axios.put(`${API_URL}/carts/${id}`, cart);
    return response.data;
  } catch (error) {
    handleError(error);
  }
};

export const deleteCart = async (id) => {
  try {
    await axios.delete(`${API_URL}/carts/delete-cart/${id}`);
  } catch (error) {
    handleError(error);
  }
};

// Repeat similar functions for items...
