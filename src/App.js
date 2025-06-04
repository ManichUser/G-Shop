import React from 'react';
import './App.css'
import HomePage from './pages/Home';
import Navbar from './components/navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FournisseurSpace from './pages/Fourmisseur-Space';
import Produits from './pages/produits';
import ProduitsVinted from './pages/produits-vinted';
import Achats from './pages/achats';
import AboutUs from './pages/AboutUs';
import Inscription from './pages/Inscription';
import Login from './pages/Login';
import FournisseurFormulaire from './components/formulaire_fournisseur';
import FloatingIcon from "./components/FloatingIcons";
// import GroupageCard from './components/GroupageCard';
import GroupagePage from './pages/GroupagePage';
import Footer from './components/Footer';
import ContactUs from './pages/contactUs';

function App() {
  return (
     <Router className="App">
      <Navbar/>
      <FloatingIcon/>
      <Routes>
        <Route path="/" element={<HomePage/>} />
        <Route path="/Fournisseur-Space" element={<FournisseurSpace/>} />
        <Route path="/produits" element={<Produits/>} />
        <Route path="/Produits-vinted" element={<ProduitsVinted/>} />
        <Route path="/Mes-Achats" element={<Achats/>} />
        <Route path="/Apropos" element={<AboutUs/>} />
        <Route path="/s'inscrire" element={<Inscription/>} />
        <Route path="/login" element={<Login/>} />
        <Route path='/Formulaire-Fournisseur' element={<FournisseurFormulaire/>}/>
        <Route path='/contact-us' element={<ContactUs/>}/>
        <Route path='/groupage-produit' element={<GroupagePage/>}/>
      </Routes>
      <Footer/>
     </Router>
   
  );
}

export default App;
