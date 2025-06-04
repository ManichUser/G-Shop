
import { panierServiceAPI } from "./api";


export interface CommandeRequestDTO {
  userId: string;
  productId: number;
  quantity: number;
}

export interface CommandeResponseDTO {
  id: string;
  userId: string;
  productId: number;
  quantity: number;
  status: string; 
}

// Créer une commande
export const createCommande = async (commande: CommandeRequestDTO) => {
  const response = await panierServiceAPI.post("/", commande);
  return response.data as CommandeResponseDTO;
};

// Récupérer toutes les commandes
export const getAllCommandes = async () => {
  const response = await panierServiceAPI.get("/");
  return response.data as CommandeResponseDTO[];
};

// Récupérer les commandes d’un utilisateur
export const getCommandesByUserId = async (userId: string) => {
  const response = await panierServiceAPI.get(`/user/${userId}`);
  return response.data as CommandeResponseDTO[];
};

// Récupérer les commandes d’un produit
export const getCommandesByProductId = async (productId: number) => {
  const response = await panierServiceAPI.get(`/product/${productId}`);
  return response.data as CommandeResponseDTO[];
};

// Supprimer une commande
export const deleteCommande = async (commandeId: string) => {
  await panierServiceAPI.delete(`/${commandeId}`);
};

// Mettre à jour le statut d’une commande
export const updateCommandeStatus = async (commandeId: string, status: string) => {
  const response = await panierServiceAPI.patch(`/${commandeId}/status`, null, {
    params: { status },
  });
  return response.data as CommandeResponseDTO;
};
