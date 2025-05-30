import { useState } from "react";
import { FaBars,FaTimes } from "react-icons/fa";
import './SideBar.css'
import { Link } from "react-router-dom";
export default function SideBar(){
    const [open, setOpen]=useState(false)

    return(
        <>
            <FaBars className="burger-button" onClick={()=>setOpen(true)} size={24}/>

            {open &&(<div className="sidebar-overlay"  onClick={()=>setOpen(false)} />)}
            
            <div className={`sidebar ${open?'open':''}`} >
                <div className="sidebar-header">
                    <h2 className="sidebar-title"  >Menu</h2>
                    <FaTimes size={24} className="burger-button" onClick={()=>setOpen(false)}/>
                </div>
                <nav className="sidebar-nav">
                    <Link to="/Mes-Achats" className="nav-link">Mes Achats</Link>
                    <Link to="/Formulaire-Fournisseur">Devenir Fourmisseur</Link>
                    <Link to="/Apropos" className="nav-link">A propos</Link>
                </nav>
            </div>
        </>
    )
}

