// src/components/CartForm.js
import React, { useState } from 'react';
import { createCart, updateCart } from '../service/apiService';

const CartForm = ({ cartToEdit, onSave }) => {
  const [cart, setCart] = useState(cartToEdit || { items: [] });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCart({ ...cart, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (cart.id) {
        await updateCart(cart.id, cart);
      } else {
        await createCart(cart);
      }
      onSave();
    } catch (error) {
      setError('Failed to save cart.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      {error && <p>{error}</p>}
      <label>
        Cart Items:
        <input
          type="text"
          name="items"
          value={cart.items.join(', ')}
          onChange={handleChange}
        />
      </label>
      <button type="submit">Save</button>
    </form>
  );
};

export default CartForm;
