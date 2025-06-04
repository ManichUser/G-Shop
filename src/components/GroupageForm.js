import React, { useState } from 'react';

const GroupageForm = ({ onAdd }) => {
  const [produit, setProduit] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = e => {
    e.preventDefault();
    onAdd({ produit, description, date: new Date().toISOString().slice(0, 10) });
    setProduit('');
    setDescription('');
  };

  return (
    <section>
      <h2>Créer un nouveau groupage</h2>
      <form className="form" onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nom du produit"
          value={produit}
          onChange={e => setProduit(e.target.value)}
          required
        />
        <textarea
          placeholder="Description"
          value={description}
          onChange={e => setDescription(e.target.value)}
          required
        />
        <button type="submit">Lancer le groupage</button>
      </form>
    </section>
  );
};

export default GroupageForm;
