import React from 'react';
import { Link } from 'react-router-dom';
import { FaSignInAlt,FaUserPlus} from 'react-icons/fa'
import SideBar from './SideBar';

import './navbar.css';


function Navbar() {
  return (
    <nav className="nav">
      <div className="nav-container">
        <div className="nav-content">
          <div className="nav-logo">
            <Link to="/" className="nav-logo-link">
              {/* <img className='GS-Logo' src={Logo} alt='G-shop' /> */}
              <label>G-Shop</label>
            </Link>
          </div>


          <div className="nav-menu">
            <Link to="/" className="nav-link">Accueil</Link>
            <Link to="/Fournisseur-Space" className="nav-link">Espace Fournisseur</Link>
            <Link to="/produits" className="nav-link">Produits</Link>
            <Link to="/Produits-vinted" className="nav-link">Produits Vinted</Link>
          </div>

          <div className='buttonConnection' >
            
            <Link to="/login" id="nav-link-login" className="nav-link">

              <FaSignInAlt size={24} color='rgb(7,7,150)'/>
              <label>se Connecter</label>
            </Link>
            <Link to="/s'inscrire" id='nav-link-register' className='nav-link'>
              <FaUserPlus size={24} color='rgb(7,7,150)' /> 
              <label>s'inscrire</label>
            </Link>
          </div>

          <div className='menu'>
            <SideBar/>
          </div>

        </div>
      </div>
    </nav>
  );
}

export default Navbar;
