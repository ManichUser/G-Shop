import React from 'react';

const Dashboard = () => {
  return (
    <section>
      <h2>Tableau de bord de performance</h2>
      <div className="dashboard">
        <div className="card">
          <h3>Participations</h3>
          <p>28</p>
        </div>
        <div className="card">
          <h3>Groupages Réussis</h3>
          <p>19</p>
        </div>
        <div className="card">
          <h3>Groupages Échoués</h3>
          <p>9</p>
        </div>
        <div className="card">
          <h3>Produits vendus</h3>
          <p>124</p>
        </div>
      </div>
    </section>
  );
};

export default Dashboard;
