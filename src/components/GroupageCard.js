
// src/components/GroupageCard.js
import React, { useState, useEffect } from 'react';
import './GroupageCard.css'; // Importation du fichier de style CSS

// Note sur les images :
// Si vos images sont dans le dossier 'public' (ex: public/images/monimage.jpeg),
// utilisez des chemins absolus comme '/images/monimage.jpeg' directement dans la prop 'image'.
// Si vos images sont dans le dossier 'src' (ex: src/assets/monimage.jpeg),
// vous devrez les importer en haut de ce fichier (ex: import smartphoneImage from '../assets/monimage.jpeg';)
// et ensuite utiliser la variable importée (image: smartphoneImage,).
// J'ai mis des URLs de placeholder pour la démonstration.

const GroupageCard = ({ groupage }) => {
  // Données de groupage par défaut utilisées si aucune prop 'groupage' n'est fournie.
  // Idéalement, un composant comme celui-ci devrait toujours recevoir ses données via props.
  const defaultGroupage = {
    id: 'grp-default',
    productName: 'Produit par Défaut',
    targetQuantity: 5,
    currentQuantity: 2,
    pricePerUnit: 80,
    regularPrice: 120,
    deadline: '2025-06-15T23:59:59',
    image: 'https://via.placeholder.com/150/CCCCCC/000000?text=Produit+Defaut', // URL de placeholder
    participants: [{ id: 'pA', name: 'Alpha' }, { id: 'pB', name: 'Bêta' }],
    description: "Ceci est un exemple de groupage par défaut pour la démonstration.",
  };

  const currentGroupage = groupage || defaultGroupage; // Utilise la prop 'groupage' ou les données par défaut.

  const { // Déstructuration des propriétés de l'objet 'currentGroupage' pour un accès facile.
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

  const progress = (currentQuantity / targetQuantity) * 100; // Calcul du pourcentage de progression.
  const remainingQuantity = targetQuantity - currentQuantity; // Calcul des unités restantes.
  const timeLeft = new Date(deadline).getTime() - new Date().getTime(); // Calcul du temps restant en millisecondes.
  const [timeRemaining, setTimeRemaining] = useState(timeLeft > 0 ? timeLeft : 0); // État pour le compte à rebours.

  useEffect(() => { // Hook d'effet pour gérer le compte à rebours.
    if (timeRemaining <= 0) return; // Arrête le timer si le temps est écoulé.
    const timer = setInterval(() => { // Met à jour le temps restant toutes les secondes.
      setTimeRemaining(prevTime => prevTime - 1000);
    }, 1000);
    return () => clearInterval(timer); // Fonction de nettoyage pour arrêter le timer.
  }, [timeRemaining]); // Dépendance : l'effet se re-déclenche si 'timeRemaining' change.

  const formatTime = (ms) => { // Fonction utilitaire pour formater le temps.
    const days = Math.floor(ms / (1000 * 60 * 60 * 24));
    const hours = Math.floor((ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((ms % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((ms % (1000 * 60)) / 1000);
    return `${days}j ${hours}h ${minutes}m ${seconds}s`;
  };

  const handleParticipate = () => { // Fonction appelée au clic sur "Participer".
    alert(`Vous avez cliqué pour participer au groupage "${productName}" !`);
    // Ici : logique réelle d'appel API, gestion de l'utilisateur, etc.
  };

  return (
    <div className="groupage-card"> {/* Conteneur principal d'UNE seule carte */}
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

      <div className="groupage-actions">
        {timeRemaining > 0 && remainingQuantity > 0 ? (
          <button className="participate-button" onClick={handleParticipate}>
            Participer au Groupage
          </button>
        ) : (
          <button className="participate-button disabled" disabled>
            Groupage Terminé ou Objectif Atteint
          </button>
        )}
      </div>
    </div>
  );
};

export default GroupageCard;