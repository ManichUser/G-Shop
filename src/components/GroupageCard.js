
import React, { useState, useEffect } from 'react';
import './GroupageCard.css'; 
import casque from '../assets/casque.jpeg'




export   default function GroupageCard({ groupage,hideForm=false }) {

  const defaultGroupage = {
    id: 'grp-default',
    productName: 'Produit par Défaut',
    targetQuantity: 5,
    currentQuantity: 2,
    pricePerUnit: 80,
    regularPrice: 120,
    deadline: '2025-06-15T23:59:59',
    image: casque,
    participants: [{ id: 1, name: 'Bob' }, { id: 1, name: 'Alexia' }],
    description: "Ceci est un exemple de groupage par défaut pour la démonstration.",
  };

  const currentGroupage = groupage || defaultGroupage; 

  const { 
    productName,
    targetQuantity,
    currentQuantity,
    pricePerUnit,
    regularPrice,
    deadline,
    image,
    participants,
    description
  } = currentGroupage;

  const progress = (currentQuantity / targetQuantity) * 100;
  const remainingQuantity = targetQuantity - currentQuantity; 
  const timeLeft = new Date(deadline).getTime() - new Date().getTime();
  const [timeRemaining, setTimeRemaining] = useState(timeLeft > 0 ? timeLeft : 0); 

  useEffect(() => { 
    if (timeRemaining <= 0) return;
    const timer = setInterval(() => { 
      setTimeRemaining(prevTime => prevTime - 1000);
    }, 1000);
    return () => clearInterval(timer); 
  }, [timeRemaining]); 

  const formatTime = (ms) => { 
    const days = Math.floor(ms / (1000 * 60 * 60 * 24));
    const hours = Math.floor((ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((ms % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((ms % (1000 * 60)) / 1000);
    return `${days}j ${hours}h ${minutes}m ${seconds}s`;
  };

  const handleParticipate = () => { 
    alert(`Vous avez cliqué pour participer au groupage "${productName}" !`);
    
    
  };

  return (
    <div className="groupage-card"> 
      <div className="groupage-header">
        <img src={image} alt={productName} className="groupage-image" />
        <div className="groupage-info">
          <h2>{productName}</h2>
          <p className="groupage-description">{description}</p>
        </div>
      </div>

      <div className="groupage-details">
        <div className="price-info">
          <p className="regular-price">Prix normal: <span>{regularPrice}€</span></p>
          <p className="groupage-price">Prix groupage: <span>{pricePerUnit}€</span></p>
        </div>

        <div className="quantity-info">
          <p>Objectif: <strong>{targetQuantity} unités</strong></p>
          <p>Actuel: <strong>{currentQuantity} unités</strong> ({progress.toFixed(0)}%)</p>
          <div className="progress-bar-wrapper">
            <div className="progress-bar" style={{ width: `${progress}%` }}></div>
          </div>
          {remainingQuantity > 0 ? (
            <p className="remaining-quantity">Plus que <strong>{remainingQuantity} unités</strong> pour atteindre l'objectif !</p>
          ) : (
            <p className="achieved-goal">Objectif atteint !</p>
          )}
        </div>

        <div className="deadline-info">
          {timeRemaining > 0 ? (
            <p>Temps restant: <strong>{formatTime(timeRemaining)}</strong></p>
          ) : (
            <p className="deadline-passed">Date limite dépassée !</p>
          )}
        </div>
      </div>

      <div className="groupage-participants">
        <h3>Participants ({participants.length}/{targetQuantity})</h3>
        <div className="participants-list">
          {participants.map((p, index) => (
            <span key={p.id || index} className="participant-tag">{p.name}</span>
          ))}
          {participants.length < targetQuantity && (
            <span className="participant-tag placeholder">+ {targetQuantity - participants.length}</span>
          )}
        </div>
      </div>

{ !hideForm && (<div className="groupage-actions">
        {timeRemaining > 0 && remainingQuantity > 0 ? (
          <button className="participate-button" onClick={handleParticipate}>
            Participer au Groupage
          </button>
        ) : (
          <button className="participate-button disabled" disabled>
            Groupage Terminé ou Objectif Atteint
          </button>
        )}
      </div>)}
    </div>
  );
};

