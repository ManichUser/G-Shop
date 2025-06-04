import React, { useState } from 'react';
import './contactUs.css';
import teamPhoto from './../assets/gshop-team.jpg'; // Mets ici une image adaptée à G-Shop

const ContactUs = () => {
  const [message, setMessage] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Message envoyé à G-Shop:', { name, email, message });
    // Ajoute ici ton appel API backend si besoin
  };

  return (
    <section className="contact-us">
      <div className="contact-content">

        <div className="contact-info">
          <img src={teamPhoto} alt="Équipe G-Shop" />
          <h3>Notre Équipe</h3>
          <p>
            L'équipe G-Shop est animée par la volonté de créer une plateforme de vente collaborative et responsable. 
            Nous sommes à votre écoute pour améliorer votre expérience.
          </p>
        </div>


        <form onSubmit={handleSubmit} className="contact-form">
          <h1 className="contact-title">Contact <span style={{ color: 'rgb(7,7,150)' }}>G-Shop</span></h1>
          <div className="input-group">
            <input
              type="text"
              placeholder="Votre nom"
              className="input-field"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <input
              type="email"
              placeholder="Votre email"
              className="input-field"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <textarea
              placeholder="Votre message"
              className="input-field"
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              required
            ></textarea>
          </div>
          <button type="submit" className="submit-btn">
            Envoyer le message
          </button>
        </form>
      </div>
    </section>
  );
};

export default ContactUs;
