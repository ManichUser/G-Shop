import React from "react";
import "./aboutUs.css";

const AboutUs = () => {
  return (
    <>
      <section className="about-section fade-in">
        <div className="about-container">
          <h2 className="about-title slide-up">G-Shop, c’est quoi ?</h2>
          <p className="about-subtitle slide-up">
            G-Shop est une plateforme innovante qui démocratise les achats groupés
            et favorise la revente entre particuliers grâce à son espace Vinted intégré.
          </p>

          <div className="about-grid">
            <div className="about-card slide-up">
              <h3>Les achats groupés pour tous</h3>
              <p>
                Profitez de tarifs dégressifs sans avoir à commander seul en grande quantité.
                Créez ou rejoignez un groupe d’achat et consommez autrement.
              </p>
            </div>

            <div className="about-card slide-up">
              <h3>Un espace Vinted intégré</h3>
              <p>
                Vendez et achetez des articles d’occasion en toute sécurité.
                G-Shop favorise une consommation durable, économique et solidaire.
              </p>
            </div>

            <div className="about-card slide-up">
              <h3>Une communauté modérée</h3>
              <p>
                G-Shop garantit un environnement sécurisé grâce à une équipe de modération
                dédiée et des outils de signalement efficaces.
              </p>
            </div>

            <div className="about-card slide-up">
              <h3>Sécurité et transparence</h3>
              <p>
                Paiements sécurisés, données protégées et assistance complète :
                achetez, vendez et participez aux achats groupés en toute sérénité.
              </p>
            </div>
          </div>

          <div className="about-footer slide-up">
            <p>
              Réalisé avec passion par l'équipe <span className="team-name">ByteMastermind</span>.
            </p>
          </div>
        </div>
      </section>
    </>
  );
};

export default AboutUs;
