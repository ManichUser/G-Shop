import axios from "axios";
import { payementServiceAPI } from "./api";

export const initiatePayment = async (paymentData: {
  externalOrderId: string;
  amount: number;
  currency: string;
  paymentMethod: string;
  buyerId: string;
}) => {
  const response = await payementServiceAPI.post("/payments/initiate", paymentData);
  return response.data;
};

// Récupérer le statut d’un paiement
export const getPaymentStatus = async (paymentId: string) => {
  const response = await payementServiceAPI.get(`/payments/${paymentId}/status`);
  return response.data;
};

// Simuler un callback Orange Money
export const sendOrangeMoneyCallback = async (callbackData: {
  externalTransactionId: string;
  status: string;
  amount: number;
  currency: string;
}) => {
  const response = await payementServiceAPI.post("/callbacks/orange-money", callbackData);
  return response.data;
};

// Simuler un callback MTN MoMo
export const sendMtnMomoCallback = async (callbackData: {
  externalTransactionId: string;
  status: string;
  amount: number;
  currency: string;
}) => {
  const response = await payementServiceAPI.post("/callbacks/mtn-momo", callbackData);
  return response.data;
};
