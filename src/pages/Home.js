import React from "react";
import SearchBar from "../components/search_bar";
import { useState } from "react";
import ProductCard from "../components/productCard";
import AirepordImg from '../assets/Airpord.png';
import MontreImg from '../assets/montre_connecter.png';
import { ChevronLeft,ChevronRight, Home,TabletSmartphone} from "lucide-react";
import { FaChevronLeft,FaChevronRight,FaHome,FaHeartbeat,FaTshirt,FaMobileAlt } from "react-icons/fa";
import './home.css'



const ProductList=[
    {
        id:1,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg,
        NbreStar:3.5

    },
    {
        id:2,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:5
    },
    {
        id:3,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg,
        NbreStar:3.5

    },
    {
        id:4,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:5
    },
    {
        id:5,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg,
        NbreStar:3.5

    },
    {
        id:6,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:5
    },
    {
        id:7,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:3.5
    },
    {
        id:8,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg,
        NbreStar:5

    },
    {
        id:10,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:4.5
    },
    {
        id:11,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:2.5
    },
    {
        id:12,
        Nom:'Montre Connecter',
        price:4000,
        img:MontreImg,
        NbreStar:4

    },
    {
        id:13,
        Nom:'Air pord',
        price:2000,
        img:AirepordImg,
        NbreStar:3
    }
]
const categories = [
    {
        id: 1,
        name: "Electronique",
        description: `Decouvrez les dernieres tendances en electronique et restez connecte  au monde`,
        icon: <TabletSmartphone size={70} className="cath-icon" />
    },
    {
        id: 2,
        name: "Mode",
        description: `Habiller vous avec style et faites tourner les tetes avec nos vetements de premier choix a des prix imbattable `,
        icon: <FaTshirt size={70} className="cath-icon" />    
    },
    {
        id: 3,
        name: "Maison et Jardin",
        description: `Creez votre espace de reve avec nos produits pour la maison et le jardin `,
        icon: <Home size={70} className="cath-icon"/>
    },
    {
        id: 4,
        name: "Beaute et Bien etre",
        description: `Prenez soin de vous et rayonnez de sante et de beaute avec nos produits de soins personnels`,
        icon: <FaHeartbeat size={70} className="cath-icon" />
    }
];


export default function HomePage(){
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
                        <div className='product-popular'>
                            <ProductCard
                                id={p.id} 
                                ProductName={p.Nom}
                                price={p.price}
                                img={p.img}
                                StarNbr={p.NbreStar}
                            />
                        </div>))}
                    
                    </div>
                    <div>
                        <div  id="LeftChevron" className="button-fleche">
                            <FaChevronLeft color="white"  size={55}/>
                        </div>
                        <div id="RightChevron" className="button-fleche">
                            <FaChevronRight color="white" size={55}/>
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

                <section className="section2" >

                    <h2 className="title-section" >Cathegories </h2>
            <div className="Cath-selection">
                    {categories.map((categorie) => (
                        <div key={categorie.id} className="Cath-card">
                            {categorie.icon}
                            <h3 style={{color:'black'}}>{categorie.name}</h3>
                            <p style={{color:'black'}}>{categorie.description}</p>
                        </div>
                    ))}
            </div>
                </section>

                <section className="section3">
                <h1 className="title-section">Nos produits</h1>
                <div className="product-container">
                        {ProductList.map((p)=>(
                            <div className='produit-vinted'>
                            <ProductCard
                                id={p.id} 
                                ProductName={p.Nom}
                                price={p.price}
                                img={p.img}
                                StarNbr={p.NbreStar}
                                hideStar={true}
                            />
                            </div>
                            ))}
                    </div>
                </section>

                </div>
                
        </div>
    )
}