
import axios from "axios";

const BASE_URL = "http://192.168.X.X"; 

// Auth microservice 
export const authAPI = axios.create({
  baseURL: `${BASE_URL}:8081/api/auth`,
});

// Product microservice 
export const productServiceAPI = axios.create({
  baseURL: `${BASE_URL}:8086/api/produits`,
});

// Panier microservice
export const panierServiceAPI = axios.create({
  baseURL: `${BASE_URL}:8082/api/panier`,
});

// Vinted product microservice 
export const vintedProductAPI = axios.create({
  baseURL: `${BASE_URL}:8085/api/vinted`,
});
