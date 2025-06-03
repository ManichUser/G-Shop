
import { vintedProductAPI } from "./api";

export const getVintedProducts = async () => {
  const response = await vintedProductAPI.get("/");
  return response.data;
};

export const makeOffer = async (productId: string, userId: string, price: number) => {
  const response = await vintedProductAPI.post(`/offer`, {
    productId,
    userId,
    price,
  });
  return response.data;
};
