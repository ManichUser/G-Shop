import './Formulaire_fournisseur.css';
// import { useStep } from '../hooks/StepContext';
import React, { useState } from 'react';

export default function FournisseurFormulaire () {
  const [step, setStep] = useState(1);
  const nextStep = () => setStep(step + 1);
  const prevStep = () => setStep(step - 1);
  const [formData, setFormData] = useState({
    NomDuFournisseurOuEntreprise : '',
    StatutJuridique :'',
    NuméroImmatriculation :'',
    NIF:'',
    AdresseComplète :'',
    PaysVille :'',
    Téléphone :'',
    EmailProfessionnel :'',
    SiteWeb :'',
    NomCompletResp :'',
    FonctionResp :'',
    TéléphoneResp :'',
    EmailResp :'',
    NumérodePièceIdentitéResp :'',
    Nom_de_la_banque :'',
    Nom_du_titulaire_du_compte :'',
    Numéro_de_compte_RIB :'',
    IBAN_SWIFT :'',
    Catégories_de_produits :'',
    Quantité_moyenne_disponible_mois :0 ,
    Délais_de_livraison_estimés :'',
    Zone_de_livraison :'',
    Conditions_de_retour_acceptées:'',
    Garantie_offerte :'',
    Service_client_disponible:'',
    Horaires_du_service_client :'',
    Nom_engagement:''
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };
  return (
    <div className="form-container">
      <h1  className='title' >Formulaire d'inscription Fournisseur</h1>
      <form className='form'>
  { step === 1 && ( <div className='etape-1'>
          <h2 className='h2'>Informations Générales</h2 >
          <label className='label' ><span style={{color:'red'}}>* </span>Nom du fournisseur / entreprise :</label>
          <input className='input' 
          value={formData.NomDuFournisseurOuEntreprise} 
          onChange={(e)=>setFormData({...formData,NomDuFournisseurOuEntreprise:e.target.value})}
          required type="text" name="entreprise" />

          <label className='label' ><span style={{color:'red'}}>* </span> Statut juridique :</label>
          <input className='input'  type="text" name="statut" />

          <label className='label' > <span style={{color:'red'}}>* </span> Numéro d'immatriculation :</label>
          <input className='input'  required type="text" name="immatriculation" />

          <label className='label' >Numéro d’identification fiscale (NIF) :</label>
          <input className='input'  type="text" name="nif" />

          <label className='label' >Adresse complète :</label>
          <select className='textarea'  name="adresse" />

          <label className='label' ><span style={{color:'red'}}>* </span> Pays / Ville :</label>
          <input className='input' required type="text" name="ville" />

          <label className='label' >Téléphone :</label>
          <input className='input'required  type="tel" name="telephone" />

          <label className='label' > <span style={{color:'red'}}>* </span> Email professionnel :</label>
          <input className='input' required type="email" name="email" />

          <label className='label' >Site web :</label>
          <input className='input'  type="url" name="site" />

        </div>)}

     { step === 2 && (<div className='etape-2' >
          <h2 className='h2'>Responsable</h2>
          <label className='label' > {"Ici vous renseigner des informations sur la personne qui s'occupera de faire les vente sur la plate-forme" }</label>
          <label className='label'  ><span style={{color:'red'}}>* </span> Nom complet :</label>
          <input className='input' required type="text" name="nom_responsable" />

          <label className='label' >Fonction :</label>
          <input className='input'  type="text" name="fonction" />

          <label className='label' ><span style={{color:'red'}}>* </span>Téléphone {"(renseigner le numero de telephone whatsApp)"}  :</label>
          <input className='input' required type="tel" name="tel_responsable" />

          <label className='label' >Email :</label>
          <input className='input'  type="email" name="email_responsable" />

          <label className='label' ><span style={{color:'red'}}>* </span>Numéro de pièce d'identité :</label>
          <input className='input' required type="text" name="piece_id" />
        </div>)}

        {step === 3 && (<div className='etape-3'>
          <h2 className='h2'>Informations Bancaires</h2>
          <label className='label' >Nom de la banque :</label>
          <input className='input'  type="text" name="banque" />

          <label className='label' >Nom du titulaire du compte :</label>
          <input className='input'  type="text" name="titulaire_compte" />

          <label className='label' >Numéro de compte / RIB :</label>
          <input className='input'  type="text" name="rib" />

          <label className='label' >IBAN / SWIFT :</label>
          <input className='input'  type="text" name="iban" />
        </div>)}

        { step === 4 && (<div className='etape-4'>
          <h2 className='h2'>Produits</h2>
          <label className='label' > <span style={{color:'red'}}>* </span> Catégories de produits :</label>
          <input  type='text' className='input' required  name="categories" />

          <label className='label' >Quantité moyenne disponible / mois :</label>
          <input className='input' type="number" min={0} name="quantite" />

          <label className='label' >Délais de livraison estimés :</label>
          <input className='input'  type="text" name="delais" />

          <label className='label' >Zone de livraison :</label>
          <select className='select' name="zone">
            <option value="locale">Locale</option>
            <option value="nationale">Nationale</option>
          </select>

        </div>)}

       { step === 5 && ( <div className='etape-5' >
        <h2 className='h2'>Politique de Vente</h2>
        <label className='label' > <span style={{color:'red'}}>* </span> Conditions de retour acceptées ?</label>
        <select className='select' required name="retour">
          <option value="oui">Oui</option>
          <option value="non">Non</option>
        </select>

        <label className='label' >Garantie offerte :</label>
        <input className='input'  type="text" name="garantie" />

        <label className='label' >Service client disponible ?</label>
        <select className='select' name="service_client">
          <option value="oui">Oui</option>
          <option value="non">Non</option>
        </select>

        <label className='label' >Horaires du service client :</label>
        <input className='input'  type="text" name="horaires" />
        </div>)}

      {step === 6 && (<div className='etape-6'>
        <h2 className='h2' >Engagement</h2>
          <p>Je certifie que les informations fournies sont exactes et je m’engage à respecter les conditions d’utilisation de la plateforme.</p>
          <label className='label' ><span style={{color:'red'}}>* </span>Nom :</label>
          <input className='input' required  type="text" name="nom_engagement" />
          <button className='button' type="submit">Soumettre</button>
      </div>)}
      <div className='prev-next'>
        {step > 1 && (<button className='button' type="button" onClick={prevStep} >Precedent</button>)}
        {step < 6 && (<button className='button' type="button" onClick={nextStep} >Suivant</button>)}
      </div>
    </form>

  </div>
  );
};