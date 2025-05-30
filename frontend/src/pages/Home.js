import React from "react";
import SearchBar from "../components/search_bar";
import { useState } from "react";
import ProductCard from "../components/productCard";
import AirepordImg from '../assets/Airpord.png';
import MontreImg from '../assets/montre_connecter.png';
// import { ChevronLeft,ChevronRight } from "lucide-react";
import { FaChevronLeft,FaChevronRight } from "react-icons/fa";
import './home.css'


const ProductList=[
    {
        id:1,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg

    },
    {
        id:2,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
    },
    {
        id:3,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg

    },
    {
        id:4,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    },
    {
        id:5,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg

    },
    {
        id:6,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    },
    {
        id:7,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    },
    {
        id:8,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg

    },
    {
        id:10,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    },
    {
        id:11,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    },
    {
        id:12,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg

    },
    {
        id:13,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg
    }
]

export default function Home(){
    const ProductPopularContainer=document.getElementsByClassName('product-popular-container')
    
    const [currentIndex, setCurrentIndex] = useState(0);
    return(
        <div className="Home">
                <div className="search-div">
                    <SearchBar/>
                </div>
                <div style={{padding:30}}>
                <section className="section1" >
                    <h1 className="title-section">Produits Popullaires</h1>
                    <div className="product-popular-container">
                        {ProductList.map((p)=>(
                            <ProductCard
                                id={p.id} 
                                ProductName={p.Nom}
                                price={p.price}
                                img={p.img}
                            />))}
                    </div>
                    <div>
                        <div  id="LeftChevron" className="button-fleche">
                            <FaChevronLeft size={55}/>
                        </div>
                        <div id="RightChevron" className="button-fleche">
                            <FaChevronRight size={55}/>
                        </div>
                    </div>
                    <div>
                
                    <div className="indicator-container"> 
                        <div style={{height:15,width:15,backgroundColor:'gray',borderRadius:'100%'}}/>
                        <div style={{height:15,width:15,backgroundColor:'gray',borderRadius:'100%'}}/>
                        <div style={{height:15,width:15,backgroundColor:'gray',borderRadius:'100%'}}/>
                    </div>
                    
                    </div>
                </section>
                </div>
        
        </div>
    )
}