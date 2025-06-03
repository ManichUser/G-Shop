
import { authAPI } from "./api";


export const login = async (email: string, password: string) => {
  const response = await authAPI.post("/auth/login", { email, password });
  return response.data;
};


export const register = async (userData: any) => {
  const response = await authAPI.post("/auth/register", userData);
  return response.data;
};

// Switch de rôle 
export const switchRoleAuth = async (userId: number, role: string) => {
  const response = await authAPI.post(`/auth/switch-role`, null, {
    params: { userId, role },
  });
  return response.data;
};

// Récupération de l'utilisateur connecté
export const getCurrentUser = async (token: string) => {
  const response = await authAPI.get("/user/me", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// Récupération des rôles (current + disponibles)
export const getUserRoles = async (token: string) => {
  const response = await authAPI.get("/user/roles", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// Switch de rôle (depuis /api/user)
export const switchRole = async (role: string, token: string) => {
  const response = await authAPI.post(
    "/user/switch-role",
    null,
    {
      params: { role },
      headers: { Authorization: `Bearer ${token}` },
    }
  );
  return response.data;
};

// Vérifie si l'utilisateur est producteur
export const isProducer = async (token: string) => {
  const response = await authAPI.get("/user/is-producer", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// Devenir fournisseur
export const becomeProducer = async (data: any, token: string) => {
  const response = await authAPI.post("/user/devenir-fournisseur", data, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// ADMIN : désactiver un utilisateur
export const disableUser = async (userId: number, token: string) => {
  const response = await authAPI.put(`/admin/disable-user/${userId}`, null, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// ADMIN : activer un utilisateur
export const enableUser = async (userId: number, token: string) => {
  const response = await authAPI.put(`/admin/enable-user/${userId}`, null, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// ADMIN : supprimer un utilisateur
export const deleteUser = async (userId: number, token: string) => {
  const response = await authAPI.delete(`/admin/delete-user/${userId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// ADMIN : récupérer les logs d’audit
export const getAuditLogs = async (token: string) => {
  const response = await authAPI.get("/admin/audit-logs", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};

// PRODUCER : page d’accueil producteur (juste une démo)
export const getProducerWelcome = async (token: string) => {
  const response = await authAPI.get("/producer/mes-produits", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
};
