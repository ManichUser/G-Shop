import React from "react";
import { Star } from "lucide-react";
import { FaStar } from "react-icons/fa";


import './productCard.css'


export default function ProductCard({   hideName=false,
                                        hideprice=false,
                                        hideQte=false,
                                        hideimg=false,
                                        hideLocalisation=false,
                                        hideStar=false,
                                        id=0,
                                        ProductName="Default name",
                                        price=0,
                                        img='../assets/Airpord.png',
                                        Qte=0,
                                        localisation='Dschang',
                                        StarNbr=0.0
                                    }){

    return(
        <div key={id} className="ProductCard">
            {!hideimg && <img src={img} alt="produit-img"/>}
            {!hideName&&<h3 className='card-name'>{ProductName} </h3>}
            <div className="footer_ProduitCard">
            { !hideprice&&<p className='price'>{price}<span> Fr</span></p>}
            {!hideStar&&<p style={{display:'flex',justifyContent:'center',alignItems:'center',gap:5}}>
            <FaStar color="gold" size={20}/> 
            <span>{StarNbr}</span></p>}
            </div>
        </div>
    )
}