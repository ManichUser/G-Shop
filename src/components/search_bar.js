import React from "react";
import './search_bar.css'
import { Search } from "lucide-react";


export default function SearchBar(){
    return (
        <div className="SearchBar">
            <input id="input-search" type="text" placeholder="Effectuer une recherche"/>
            <button className="searchBtn" ><Search/></button>
        </div>
    )
}