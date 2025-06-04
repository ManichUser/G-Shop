import { Link } from "react-router-dom";
import "./Login.css";

export default function Login() {
  return (
    <div className="connection-page">
      <div className="connection-container">
        <div className="connection-form">
          <div className="connection-info">
            <h1>Connection</h1>
            <div>
              <label htmlFor="nom-user">Nom-user : </label>
              <input
                type="text"
                id="nom-user"
                placeholder="entrer votre nom"
              />
            </div>
            <div>
              <label htmlFor="mot-de-passe">Mot de passe : </label>
              <input
                type="password"
                id="mot-de-passe"
                placeholder="entrer votre mot de passe"
              />
            </div>
          </div>
          <div className="connection-actions">
            <p>
              vous n'avez pas de compte ? <Link to="/s'inscrire">s'inscrire ?</Link>{" "}
            </p>
            <div className="buttons-connection">
              <button type="button">Annuler</button>
              <button type="submit">Valider</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}