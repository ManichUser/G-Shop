import './FloatingIcon.css';
import {
  FaShoppingBag, FaBoxOpen, FaCreditCard, FaTags, FaTruck,
  FaGift, FaStore, FaShoppingCart, FaMoneyBillWave, FaPercent,
  FaUserTie, FaClipboardList, FaCashRegister, FaBarcode,
  FaHeadset, FaWarehouse, FaReceipt, FaQrcode, FaCartArrowDown, FaUserCheck
} from 'react-icons/fa';

import {
  ShoppingBag, Package, WalletCards, TruckIcon, ReceiptText
} from 'lucide-react';

const icons = [
  <FaShoppingBag size={48} color="blue" />,
  <FaBoxOpen color="red" size={45} />,
  <FaCreditCard size={80} color="yellow" />,
  <FaTags size={34} color="rgb(7,7,150)" />,
  <FaTruck size={30} />,
  <ShoppingBag size={30} color="#f87171" />,
  <Package size={40} color="#fb923c" />,
  <WalletCards size={28} color="#34d399" />,
  <TruckIcon size={35} color="#60a5fa" />,
  <ReceiptText size={40} color="#facc15" />,

  <FaGift size={40} color="#f472b6" />,
  <FaStore size={34} color="#4ade80" />,
  <FaShoppingCart size={40} color="#0ea5e9" />,
  <FaMoneyBillWave size={50} color="#10b981" />,
  <FaPercent size={36} color="#7c3aed" />,
  <FaUserTie size={32} color="#f87171" />,
  <FaClipboardList size={30} color="#fbbf24" />,
  <FaCashRegister size={38} color="#4b5563" />,
  <FaBarcode size={40} color="#f43f5e" />,
  <FaHeadset size={36} color="#3b82f6" />,
  <FaWarehouse size={42} color="#8b5cf6" />,
  <FaReceipt size={38} color="#a78bfa" />,
  <FaQrcode size={34} color="#16a34a" />,
  <FaCartArrowDown size={46} color="#0f766e" />,
  <FaUserCheck size={40} color="#6366f1" />,
];

export default function FloatingIcon() {
  return (
    <div className="falling-container">
      {icons.map((icon, index) => (
        <div key={index} className="icon">
          {icon}
        </div>
      ))}
    </div>
  );
}
