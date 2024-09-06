// src/components/CartList.js
import React, { useEffect, useState } from 'react';
import { getAllCarts, deleteCart } from '../service/apiService';

const CartList = () => {
  const [carts, setCarts] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchCarts = async () => {
      try {
        const data = await getAllCarts();
        setCarts(data);
      } catch (error) {
        setError('Failed to fetch carts.');
      }
    };
    fetchCarts();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteCart(id);
      setCarts(carts.filter(cart => cart.id !== id));
    } catch (error) {
      setError('Failed to delete cart.');
    }
  };

  return (
    <div>
      <h1>Cart List</h1>
      {error && <p>{error}</p>}
      <ul>
        {carts.map(cart => (
          <li key={cart.id}>
            Cart ID: {cart.id} - {cart.items.length} items
            <button onClick={() => handleDelete(cart.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CartList;
