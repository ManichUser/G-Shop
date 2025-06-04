import './inscription.css'
import { Link } from 'react-router-dom'
import GoogleLogo from '../assets/google-logo.png' 


export default function Inscription (){

    return (
        <div className="Inscription" >

                <div className="Inscription-container">
                
                <div className="left-container">
                    <label id="name-app">G-Shop</label>
                    <h1 style={{fontSize:37}} id="titre-container" >S'inscrire ou se connecter</h1>
                    <p className='message-for-user'>
                        Trouver vos produits sur <span style={{color:'rgb(7, 7, 150',fontWeight:'bold'}} >Grouping Shop</span> et minimiser 
                        ainsi vos depenses du quotidien
                    </p>
                    <div className='btn-container' >
                        <div style={{width:250, display:'flex', cursor:'pointer', alignItems:'center'}} >
                            <p id="google-btn">Continuer avec </p>
                            <img id='logo-google' src={GoogleLogo} alt='logo-google' />
                        </div>
                        <Link to="/login" style={{width:200,color:'white'}} className='boutton' id="connection">Se connecter</Link>
                    </div>

                </div>

                <div className="right-container" >
                    
                    <form className="form-Inscription" method="POST" >
                    
                        <div className="info-Inscription">
                            <h1 style={{color:'White'}}>S'inscrire</h1>
                            <p style={{padding:7,fontSize:13,color:'white'}}> Inscrivez-vous maintenant et beneficiez de nos fonctionnalites
                                exclusives en matieres d'achats vente
                            </p>
                            <input required id="input-name" type="text" placeholder="Entrer un nom d'utilisisateur"/>
                            <input required id=" input-password" type="text" placeholder="Entre un mot de passe"/>
                            <input required id=" input-password" type="text" placeholder="Repeter le mot de passe"/>
                            <p style={{padding:7,fontSize:13,color:'red'}} className='message-for-user' id="error-msg">Le mot de passe doit avoir au moins 8 caracteres dont au minimum 1 chiffre </p>
                        </div>
                        <div className='bouttons-inscription' >
                        <input className='boutton' type='reset'  name='annuler' id='annuler-btn'/>
                            <input className='boutton' type='submit' name='login' id="Inscription-btn" />
                        </div>
                    </form>
                </div>
            </div> 

        </div>
    )

}