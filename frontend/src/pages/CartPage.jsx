import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ItemList from '../components/ItemList';
import ItemForm from '../components/ItemForm';
import { getCartById } from '../api/cartApi';

const CartPage = () => {
  const { id } = useParams();
  const [cart, setCart] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const data = await getCartById(id);
        setCart(data);
      } catch (err) {
        setError(err);
      }
    };

    fetchCart();
  }, [id]);

  if (error) return <p>Error: {error.message}</p>;
  if (!cart) return <p>Loading...</p>;

  return (
    <div className="container mx-auto py-8">
      <h1 className="text-2xl font-bold mb-4">{cart.name}</h1>
      <ItemList items={cart.items} />
      <ItemForm cartId={id} />
    </div>
  );
};

export default CartPage;
