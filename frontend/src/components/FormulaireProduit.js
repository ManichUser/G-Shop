import React, { useState } from "react";
import "./FormulaireProduits.css";

export default function FormulaireGrouppage(){
  const [quantity, setQuantity] = useState(1);
  const pricePerUnit = 1000;
  const totalPrice = quantity * pricePerUnit;

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Vous avez commandé ${quantity} unité(s) pour un total de €${totalPrice}`);
  };

  return (
    <form className="order-form" onSubmit={handleSubmit}>
      <label htmlFor="quantity">Quantité</label>
      <input
        type="number"
        id="quantity"
        min="1"
        value={quantity}
        onChange={(e) => setQuantity(Number(e.target.value))}
      />

      <div className="total-price">
        <span>Prix total</span>
        <strong>{totalPrice} FCFA</strong>
      </div>

      <button type="submit">Rejoindre le groupage</button>
    </form>
  );
};

