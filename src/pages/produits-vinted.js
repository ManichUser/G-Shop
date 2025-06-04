import './produits-vinted.css'
import ProductCard from "../components/productCard";
import AirepordImg from '../assets/Airpord.png';
import MontreImg from '../assets/montre_connecter.png';
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
export default function ProduitsVinted() {
    return(
       <div className="ProduitsVinted">
        <h1 >Ici vous avez la les produits des autres utilisateurs. </h1>
        <h2 style={{color:'black'}}>Redonner vie a vos objets chez vous avec nous.</h2>
        <div className="product-vinted-container">
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
                            </div>))}
                    </div>
       </div>
    )
}