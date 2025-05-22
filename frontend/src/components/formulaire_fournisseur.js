import './Formulaire_fournisseur.css';
import React from 'react';

const FournisseurFormulaire = () => {
  return (
    <div className="form-container">
      <h1>Formulaire d'inscription Fournisseur</h1>
      <form>
        <h2>1. Informations Générales</h2>
        <label>Nom du fournisseur / entreprise :</label>
        <input type="text" name="entreprise" />

        <label>Statut juridique :</label>
        <input type="text" name="statut" />

        <label>Numéro d'immatriculation :</label>
        <input type="text" name="immatriculation" />

        <label>Numéro d’identification fiscale (NIF) :</label>
        <input type="text" name="nif" />

        <label>Adresse complète :</label>
        <textarea name="adresse" />

        <label>Pays / Ville :</label>
        <input type="text" name="ville" />

        <label>Téléphone :</label>
        <input type="text" name="telephone" />

        <label>Email professionnel :</label>
        <input type="email" name="email" />

        <label>Site web :</label>
        <input type="url" name="site" />

        <h2>2. Responsable</h2>
        <label>Nom complet :</label>
        <input type="text" name="nom_responsable" />

        <label>Fonction :</label>
        <input type="text" name="fonction" />

        <label>Téléphone :</label>
        <input type="text" name="tel_responsable" />

        <label>Email :</label>
        <input type="email" name="email_responsable" />

        <label>Numéro de pièce d'identité :</label>
        <input type="text" name="piece_id" />

        <h2>3. Informations Bancaires</h2>
        <label>Nom de la banque :</label>
        <input type="text" name="banque" />

        <label>Nom du titulaire du compte :</label>
        <input type="text" name="titulaire_compte" />

        <label>Numéro de compte / RIB :</label>
        <input type="text" name="rib" />

        <label>IBAN / SWIFT :</label>
        <input type="text" name="iban" />

        <h2>4. Produits</h2>
        <label>Catégories de produits :</label>
        <textarea name="categories" />

        <label>Quantité moyenne disponible / mois :</label>
        <input type="text" name="quantite" />

        <label>Délais de livraison estimés :</label>
        <input type="text" name="delais" />

        <label>Zone de livraison :</label>
        <select name="zone">
          <option value="locale">Locale</option>
          <option value="nationale">Nationale</option>
          <option value="internationale">Internationale</option>
        </select>

        <label>Images de produits (URL ou description) :</label>
        <textarea name="images" />

        <h2>5. Politique de Vente</h2>
        <label>Conditions de retour acceptées ?</label>
        <select name="retour">
          <option value="oui">Oui</option>
          <option value="non">Non</option>
        </select>

        <label>Garantie offerte :</label>
        <input type="text" name="garantie" />

        <label>Service client disponible ?</label>
        <select name="service_client">
          <option value="oui">Oui</option>
          <option value="non">Non</option>
        </select>

        <label>Horaires du service client :</label>
        <input type="text" name="horaires" />

        <h2>6. Engagement</h2>
        <p>Je certifie que les informations fournies sont exactes et je m’engage à respecter les conditions d’utilisation de la plateforme.</p>
        <label>Nom :</label>
        <input type="text" name="nom_engagement" />

        <label>Date :</label>
        <input type="date" name="date" />

        <button type="submit">Soumettre</button>
      </form>
    </div>
  );
};

export default FournisseurFormulaire;

