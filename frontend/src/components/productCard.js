import React from "react";

import './productCard.css'


export default function ProductCard({   hideName=false,
                                        hideprice=false,
                                        hideQte=false,
                                        hideimg=false,
                                        hideLocalisation=false,
                                        id=0,
                                        ProductName="Default name",
                                        price=0,
                                        img='../assets/Airpord.png',
                                        Qte=0,
                                        localisation='Dschang',

                                    }){

    return(
        <div key={id} className="ProductCard">
           {!hideimg && <img src={img} alt="produit-img"/>}
            {!hideName&&<label>{ProductName} </label>}
           { !hideprice&&<p>{price}<span> Fr</span></p>}
        </div>
    )
}