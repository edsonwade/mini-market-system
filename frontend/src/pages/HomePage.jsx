import React, { useEffect, useState } from 'react';
import CartList from '../components/CartList';
import { getCarts } from '../api/cartApi';

const HomePage = () => {
  const [carts, setCarts] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCarts = async () => {
      try {
        const data = await getCarts(page);
        setCarts(data.content); // Adjust based on the actual response structure
        setTotalPages(data.totalPages); // Adjust based on the actual response structure
      } catch (error) {
        setError('Failed to fetch carts.');
      }
    };

    fetchCarts();
  }, [page]);

  if (error) return <p>{error}</p>;

  return (
    <div className="container mx-auto py-8">
      <h1 className="text-2xl font-bold mb-4">Carts</h1>
      <CartList carts={carts} />
      <div className="pagination mt-4">
        <button 
          onClick={() => setPage(page > 0 ? page - 1 : 0)}
          disabled={page === 0}
          className="px-4 py-2 bg-blue-500 text-white rounded mr-2"
        >
          Previous
        </button>
        <span>Page {page + 1} of {totalPages}</span>
        <button 
          onClick={() => setPage(page < totalPages - 1 ? page + 1 : totalPages - 1)}
          disabled={page >= totalPages - 1}
          className="px-4 py-2 bg-blue-500 text-white rounded ml-2"
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default HomePage;
