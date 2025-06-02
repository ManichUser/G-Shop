
// src/pages/GroupagesPage.js

// 1. Assurez-vous que ces imports sont bien présents
import React, { useState, useEffect } from 'react';
import GroupageCard from '../components/GroupageCard'; // <--- TRÈS IMPORTANT : Chemin correct vers le composant GroupageCard
import './GroupagesPage.css'; // Assurez-vous que ce fichier CSS existe et est bien nommé

// 2. Importez vos images ici si elles sont dans src/assets
// Assurez-vous que ces chemins sont corrects par rapport à la position de GroupagesPage.js
import smartphoneImage from '../assets/téléchargement (1).jpeg'; // Renommez si possible (ex: telechargement_1.jpeg)
import casqueImage from '../assets/téléchargement.jpeg';     // Renommez si possible (ex: telechargement_2.jpeg)
// Si vos images sont dans 'public/', vous n'avez pas besoin d'importer ici, utilisez des chemins comme '/assets/nom_image.jpeg'

function GroupagesPage() {
  // 3. allGroupagesData : Utilise les variables d'images importées
  const allGroupagesData = [
    {
      id: 'grp-001',
      productName: 'Smartphone X Dernier Modèle',
      targetQuantity: 10,
      currentQuantity: 6,
      pricePerUnit: 500,
      regularPrice: 700,
      deadline: '2025-06-30T23:59:59',
      image: smartphoneImage, // Utilisez la variable importée
      participants: [{ id: 'p1', name: 'Alice' }, { id: 'p2', name: 'Bob' }],
      description: "Rejoignez notre groupage pour obtenir le nouveau Smartphone X à un prix incroyable ! Quantité limitée.",
    },
    {
      id: 'grp-002',
      productName: 'Casque Bluetooth Pro',
      targetQuantity: 20,
      currentQuantity: 15,
      pricePerUnit: 120,
      regularPrice: 180,
      deadline: '2025-07-15T18:00:00',
      image: casqueImage, // Utilisez la variable importée
      participants: [{ name: 'Marie' }, { name: 'Pierre' }, { name: 'Luc' }],
      description: "Obtenez le casque pro avec une réduction de 30% !",
    },
    {
      id: 'grp-003',
      productName: 'Souris Gamer Optique',
      targetQuantity: 5,
      currentQuantity: 5,
      pricePerUnit: 30,
      regularPrice: 50,
      deadline: '2025-05-20T10:00:00',
      image: 'src/assets/montre_connecter.png', // URL de placeholder ou votre chemin correct pour cette image
      participants: [{ name: 'Alex' }, { name: 'Sophie' }, { name: 'Tom' }, {name: 'Sara'}, {name: 'Chris'}],
      description: "Groupage terminé pour cette souris ergonomique de haute précision.",
    },
  ];

  // Ces lignes sont commentées car vous utilisez des données statiques.
  // Décommentez-les si vous implémentez la récupération de données dynamiques via une API.
  // const [groupages, setGroupages] = useState([]);
  // useEffect(() => {
  //   setGroupages(allGroupagesData);
  // }, []);


  return (
    <div className="groupages-page-container"> {/* Conteneur global de la page */}
      <h1>Tous les Groupages Disponibles</h1> {/* Titre de la page */}
      <div className="groupage-cards-wrapper"> {/* <--- REMIS CE CONTENEUR ET LE MAP */}
        {allGroupagesData.map(groupage => ( // Mappez sur vos données pour rendre chaque carte
          <GroupageCard key={groupage.id} groupage={groupage} />
        ))}
        {/* Si vous avez décommenté le useState et useEffect pour des données dynamiques, vous feriez :
        {groupages.map(groupage => (
          <GroupageCard key={groupage.id} groupage={groupage} />
        ))}
        */}
      </div>
    </div>
  );
}

export default GroupagesPage;