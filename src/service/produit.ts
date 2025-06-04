import { productServiceAPI } from "./api";

// Récupérer tous les produits
export const getAllProducts = async () => {
  const response = await productServiceAPI.get("/");
  return response.data;
};

// Récupérer un produit par son ID
export const getProductById = async (id: string | number) => {
  const response = await productServiceAPI.get(`/${id}`);
  return response.data;
};

// Créer un nouveau produit
export const createProduct = async (produit: any) => {
  const response = await productServiceAPI.post("/", produit);
  return response.data;
};

// Mettre à jour un produit avec son ID
export const updateProduct = async (
  id: string | number,
  updatedData: any
) => {
  const response = await productServiceAPI.put(`/${id}`, updatedData);
  return response.data;
};

// Supprimer un produit
export const deleteProduct = async (id: string | number) => {
  const response = await productServiceAPI.delete(`/${id}`);
  return response.data;
};

// Récupérer les produits d’un grossiste spécifique
export const getProductsByGrossiste = async (idGrossiste: string | number) => {
  const response = await productServiceAPI.get(`/grossiste/${idGrossiste}`);
  return response.data;
};
