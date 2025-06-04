
import axios from "axios";

const BASE_URL = "http://192.168.56.1"; 

// Auth microservice 
export const authAPI = axios.create({
  baseURL: `${BASE_URL}:8087/api/auth`,
});

// Product microservice 
export const productServiceAPI = axios.create({
  baseURL: `${BASE_URL}:8090/api/produits`,
});

// Panier microservice
export const panierServiceAPI = axios.create({
  baseURL: `${BASE_URL}:8082/api/panier`,
});

// Vinted product microservice 
export const vintedProductAPI = axios.create({
  baseURL: `${BASE_URL}:8085/api/vinted`,
});

//payement service microservice
export const payementServiceAPI = axios.create({
    baseURL: `${BASE_URL}:8091/api/produits`,
  });