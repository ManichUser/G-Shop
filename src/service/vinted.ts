import { vintedProductAPI } from "./api";

// 📦 Produits

// Tous les produits
export const getVintedProducts = async () => {
  const response = await vintedProductAPI.get("/");
  return response.data;
};

// Produits disponibles uniquement
export const getAvailableVintedProducts = async () => {
  const response = await vintedProductAPI.get("/disponible");
  return response.data;
};

// Produits d’un utilisateur
export const getUserProducts = async (userId: string) => {
  const response = await vintedProductAPI.get(`/utilisateur/${userId}`);
  return response.data;
};

// Produits d’un utilisateur par statut
export const getUserProductsByStatus = async (userId: string, status: string) => {
  const response = await vintedProductAPI.get(`/utilisateur/${userId}/status/${status}`);
  return response.data;
};

// Produits par nom
export const searchProductsByName = async (nomProduit: string) => {
  const response = await vintedProductAPI.get(`/recherche`, {
    params: { nomProduit },
  });
  return response.data;
};

// Produits par catégorie
export const getProductsByCategory = async (categorie: string) => {
  const response = await vintedProductAPI.get(`/categorie/${categorie}`);
  return response.data;
};

// Ajouter un produit
export const addVintedProduct = async (product: any) => {
  const response = await vintedProductAPI.post("/", product);
  return response.data;
};

// 📨 Offres

// Créer une offre pour un produit donné
export const makeOffer = async (productId: string, offer: any) => {
  const response = await vintedProductAPI.post(`/${productId}/offres`, offer);
  return response.data;
};

// Valider une offre
export const validateOffer = async (productId: string, offerId: number) => {
  const response = await vintedProductAPI.put(`/${productId}/offres/${offerId}/valider`);
  return response.data;
};

// Obtenir toutes les offres pour un produit
export const getOffersByProduct = async (productId: string) => {
  const response = await vintedProductAPI.get(`/offres/produit/${productId}`);
  return response.data;
};

// Obtenir toutes les offres faites par un acheteur
export const getOffersByBuyer = async (buyerId: string) => {
  const response = await vintedProductAPI.get(`/offres/acheteur/${buyerId}`);
  return response.data;
};

// Changer le statut d’une offre
export const changeOfferStatus = async (offerId: string, newStatus: string) => {
  const response = await vintedProductAPI.put(`/offres/${offerId}/statut`, null, {
    params: { statut: newStatus },
  });
  return response.data;
};
