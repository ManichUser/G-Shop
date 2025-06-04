import React from 'react';

const groupagesFictifs = [
  { id: 1, produit: 'Produit A', statut: 'Accepté', date: '2025-06-01' },
  { id: 2, produit: 'Produit B', statut: 'Refusé', date: '2025-06-02' },
  { id: 3, produit: 'Produit C', statut: 'En attente', date: '2025-06-03' },
];

export default function GroupageHistory  ()  {
  return (
    <section>
      <h2>Historique des groupages</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Produit</th>
            <th>Statut</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {groupagesFictifs.map(item => (
            <tr key={item.id}>
              <td>{item.produit}</td>
              <td>{item.statut}</td>
              <td>{item.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};


