// src/App.js
import React, { useState } from 'react';
import CartList from './components/CartList';
import CartForm from './components/CartForm';

const App = () => {
  const [selectedCart, setSelectedCart] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const handleSave = () => {
    setSelectedCart(null);
    setRefresh(!refresh);
  };

  return (
    <div>
      <h1>Cart Management</h1>
      <CartList refresh={refresh} />
      <CartForm cartToEdit={selectedCart} onSave={handleSave} />
    </div>
  );
};

export default App;
