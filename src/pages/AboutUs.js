import './aboutUs.css';
// import GrandSage from './../composant/grandSage';
// import teamPhoto1 from './../assets/dibakto-manich.jpg'; 
// import teamPhoto2 from './../assets/towa-josephine.jpeg';
// import teamPhoto3 from './../assets/enga-carnelle.jpeg';
// import Aipord from '../assets/Aipord.png'

function AboutUs() {
  return (
    <section className="about-us">
       <div class='GS-container'>
       {/* <GrandSage variant='normal'/> */}
       </div>
      <div className="about-content">
        <h1 className="about-title">Apropos de  G-Shop</h1>
        <p className="about-text">
         G-SHOP est votre site revolutionnaire qui vien aujourd'hui changer votre experience dans l'achat et la revente en ligne
        </p>

        
        {/* Section Team */}
        <div className="about-team">
          <div className="team-member">
            {/* <img src={teamPhoto1} alt="Dibakto Manich" /> */}
            <h3>Dibakto Manich</h3>
            <p>Développeur Fullstack</p>
          </div>
          
          <div className="team-member">
            {/* <img src={teamPhoto2} alt="Towa Josephine" /> */}
            <h3>Towa Josephine</h3>
            <p>Responsable Agent GrandSage</p>
          </div>
          
          <div className="team-member">
            {/* <img src={teamPhoto3} alt="Enga Carnelle" /> */}
            <h3>Enga Carnelle</h3>
            <p>Gestionnaire de Base de Données</p>
          </div>
        </div>
      </div>
    </section>
  );
}

export default AboutUs;

