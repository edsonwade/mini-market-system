import React, { useState } from 'react';
import { createItem } from '../api/itemApi';

const ItemForm = ({ cartId }) => {
  const [serialNumber, setSerialNumber] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    createItem({ serialNumber, cartId });
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <label htmlFor="serialNumber" className="block text-sm font-medium text-gray-700">
          Serial Number
        </label>
        <input
          type="text"
          id="serialNumber"
          value={serialNumber}
          onChange={(e) => setSerialNumber(e.target.value)}
          className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none"
        />
      </div>
      <button type="submit" className="w-full py-2 px-4 bg-blue-600 text-white rounded-md">
        Add Item
      </button>
    </form>
  );
};

export default ItemForm;
