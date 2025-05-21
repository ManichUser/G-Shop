import './connection.css'


export default function Connection(){

    return (
        <div className="Connection" >

                <div className="connection-container">

                <div className="left-container">
                    <label id="name-app">UniKart</label>
                    <h1 id="titre-container" >S'inscrire ou se connecter</h1>
                    <p>
                        lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem
                    </p>
                    <div>
                        <button id="google-btn">Continuer avec Google</button>
                        <button id="register">S'inscrire</button>
                    </div>

                </div>

                <div className="right-container" >
                    
                    <form className="form-connection" method="POST" >
                        <h1>Login</h1>
                        <div classame="info-connection">
                            <input id="input-name" type="text" placeholder="Enter your user name"/>
                            <input id=" input-password" type="text" placeholder="Enter your password"/>
                            <p id="error-msg">error message</p>
                        </div>
                        <div>
                            <input type='submit' name='login' id="connection-btn" />
                        </div>
                    </form>
             
                </div>
            </div> 

        </div>
    )

}