import React, { useState } from 'react';
import './paiementCard.css';

function PaiementCard() {
  const [modePaiement, setModePaiement] = useState('carte');

  const handleChange = (event) => {
    setModePaiement(event.target.value);
  };

  return (
    <div className="paiement-container">
      <div className="paiement-card">
        <header className="paiement-header">
          <span className="gshop">G-Shop</span>
          <span className="titre">PAIEMENT</span>
        </header>

        <div className="paiement-details">
          <p><strong>Nom du produit</strong></p>
          <p>Qté: 2</p>
          <p>Montant total: <strong>25 000 F</strong></p>
        </div>

        <form className="paiement-form">
          <div className="option-paiement">
            <label>
              <input
                type="radio"
                value="carte"
                checked={modePaiement === 'carte'}
                onChange={handleChange}
              />
              Carte bancaire
            </label>
            {modePaiement === 'carte' && (
              <div className="input-with-icons">
                <input type="text" placeholder="Numéro de carte" />
                <div className="icons">
                  <img src="https://img.icons8.com/color/48/visa.png" alt="Visa" />
                  <img src="https://img.icons8.com/color/48/mastercard.png" alt="Mastercard" />
                </div>
              </div>
            )}
          </div>

          <div className="option-paiement">
            <label>
              <input
                type="radio"
                value="orange"
                checked={modePaiement === 'orange'}
                onChange={handleChange}
              />
              Orange Money
            </label>
          </div>

          <div className="option-paiement">
            <label>
              <input
                type="radio"
                value="mobile"
                checked={modePaiement === 'mobile'}
                onChange={handleChange}
              />
              <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Logo_MTN_Group.svg/1200px-Logo_MTN_Group.svg.png" alt="logo Mobile Money" className="mobile-logo" />
              Mobile Money
            </label>
            {modePaiement === 'mobile' && (
              <input type="text" placeholder="Numéro de compte à débiter" />
            )}
          </div>

          <button type="submit" className="btn-payer">PAYER</button>
        </form>
      </div>
    </div>
  );
}

export default PaiementCard;
