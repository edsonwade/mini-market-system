import React from 'react';

const ItemList = ({ items }) => {
  return (
    <ul>
      {items.map((item) => (
        <li key={item.id}>
          Serial Number: {item.serialNumber}
        </li>
      ))}
    </ul>
  );
};

export default ItemList;
