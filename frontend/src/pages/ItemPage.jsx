import React, { useState, useEffect } from 'react';
import { getCarts } from '../api/cartApi';
import CartList from '../components/CartList';

const HomePage = () => {
  const [carts, setCarts] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCarts = async () => {
      try {
        const data = await getCarts();
        setCarts(data);
      } catch (err) {
        setError(err);
      }
    };

    fetchCarts();
  }, []);

  if (error) return <p>Error: {error.message}</p>;
  if (carts.length === 0) return <p>Loading...</p>;

  return (
    <div className="container mx-auto py-8">
      <h1 className="text-2xl font-bold mb-4">Carts</h1>
      <CartList carts={carts} />
    </div>
  );
};

export default HomePage;
