import React, { useState } from 'react';
import "./Fournisseur-Space.css"
import GroupageCard from '../components/GroupageCard';

import GroupageHistory from '../components/GroupageHistory'

import GroupageForm from '../components/GroupageForm';

import Dashboard from '../components/Dashboard'
import { LucideLayoutDashboard } from 'lucide-react';
export default function FournisseurSpace(){
  const [groupages, setGroupages] = useState([]);

  const ajouterGroupage = (newGroupage) => {
    setGroupages(prev => [...prev, newGroupage]);
  };
const ListeProduitFornisseur= async ()=>{
  
}



  return (
    <main className="FournisseurSpace">
      <h1 style={{marginBottom:40}} >Groupage en Cours</h1>
      <div className='Groupage-container'>
      <div className='group-card'>
        <GroupageCard hideForm={true}/>
      </div>
      <div className='group-card'>
        <GroupageCard hideForm={true}/>
      </div>
      <div className='group-card'>
        <GroupageCard hideForm={true}/>
      </div>
      <div className='group-card'>
        <GroupageCard hideForm={true}/>
      </div>
      </div>
      <GroupageForm onAdd={ajouterGroupage} />
      <GroupageHistory />
      <Dashboard />
    </main>
  );
};


