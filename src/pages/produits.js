import React, { useState } from "react"
import FormulaireGrouppage from "../components/FormulaireProduit"
import { Link } from "react-router-dom";
import "./produits.css"; // pour styliser si besoin

const produitsData = [
  {
    categorie: "Electronique",
    produits: [
      { nom: "Laptop", prix: 599, image: "laptop.jpg" },
      { nom: "Headphones", prix: 249, image: "headphones.jpg" },
      { nom: "Smartphone", prix: 599, image: "smartphone.jpg" },
      { nom: "Smartwatch", prix: 249, image: "smartwatch.jpg" },
      
    ],
  },
  {
    categorie: "Maison et Jadin",
    produits: [
      { nom: "Sofa", prix: 499, image: "sofa.jpg" },
      { nom: "Coffee table", prix: 299, image: "coffeetable.jpg" },
      { nom: "Coffee table", prix: 299, image: "coffeetable.jpg" },
      { nom: "Coffee table", prix: 299, image: "coffeetable.jpg" },
    ],
  },
  {
    categorie: "Mode",
    produits: [
      { nom: "T-shirt", prix: 20, image: "tshirt.jpg" },
      { nom: "Hoodie", prix: 20, image: "hoodie.jpg" },
      { nom: "T-shirt", prix: 20, image: "tshirt.jpg" },
      { nom: "Hoodie", prix: 20, image: "hoodie.jpg" },
    ],
  },
  {
    categorie: "Sante et Bien etre",
    produits: [
      { nom: "Huile de nem", prix: 20, image: ".././assets/casque.jpeg" },
      { nom: "Hoodie", prix: 20, image: "hoodie.jpg" },
      { nom: "T-shirt", prix: 20, image: "tshirt.jpg" },
      { nom: "Hoodie", prix: 20, image: "hoodie.jpg" },
    ],
  },
  
];

export default function Produits() {
  const [showForm, setShowForm] = useState(null);

  return (
    <div className="produits-container">
      {produitsData.map((cat, i) => (
        <div key={i} className="categorie-section">
          <h2 style={{marginBottom:25, fontSize:30}}>{cat.categorie}</h2>
          <div className="produits-liste">
            {cat.produits.map((prod, j) => (
              <div key={j} className="produit-card">
                <img src={`/images/${prod.image}`} alt={prod.nom} className="produit-image" />
                <h3 style={{color:'black'}} >{prod.nom}</h3>
                <p>${prod.prix}</p>
                <Link to='/groupage-produi' style={{width:200,fontSize:15,color:'rgb(7,7,230)'}} id="connection">
                    Afficher plus de details
                </Link>
                <button onClick={() => setShowForm(`${cat.categorie}-${j}`)}>
                  Participer au groupage
                </button>
                {showForm === `${cat.categorie}-${j}` && (
                  <FormulaireGrouppage />
                )}
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
}