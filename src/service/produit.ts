
import { productServiceAPI } from "./api";

export const getAllProducts = async () => {
  const response = await productServiceAPI.get("/");
  return response.data;
};

export const getProductById = async (id: string) => {
  const response = await productServiceAPI.get(`/${id}`);
  return response.data;
};
