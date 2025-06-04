import { Link } from 'react-router-dom';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="gshop-footer">
      <div className="footer-content">
        <div className="footer-section about">
          <h2>G-Shop</h2>
          <p>
            G-Shop, c’est une nouvelle façon d’acheter et de vendre : collaborative, solidaire et intelligente.
            Profitez des achats groupés ou donnez une seconde vie à vos objets.
          </p>
        </div>

        <div className="footer-section links">
          <h3>Navigation</h3>
          <ul>
            <li><Link to="/">Accueil</Link></li>
            <li><Link to="/Fournisseur-Space">Espace Fournisseur</Link></li>
            <li><Link to="/produits">Produits</Link></li>
            <li><Link to="/Produits-vinted">Produits Vinted</Link></li>
            <li><Link to="/contact-us">Contact</Link></li>
          </ul>
        </div>

        <div className="footer-section follow">
          <h3>Suivez-nous</h3>
          <ul className="socials">
            <li><a href="https://facebook.com" target="_blank" rel="noopener noreferrer">Facebook</a></li>
            <li><a href="https://instagram.com" target="_blank" rel="noopener noreferrer">Instagram</a></li>
            <li><a href="https://twitter.com" target="_blank" rel="noopener noreferrer">Twitter</a></li>
          </ul>
        </div>
      </div>

      <div className="footer-bottom">
        <p style={{color:'White'}} >&copy; 2025 <span>G-Shop</span> | Réalisé par <strong>ByteMastermind</strong></p>
      </div>
    </footer>
  );
};

export default Footer;
