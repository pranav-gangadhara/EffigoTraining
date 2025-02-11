import React from "react";
import { useParams } from "react-router-dom";
import { speakerData } from "../data/speaker";

import Navbar from "../components/Navbar";
import { useCart } from "../context/CartContext";

const SpeakerSingle = () => {
  const { id } = useParams();
  const { addToCart } = useCart();

  // Convert id to a number for correct comparison
  const product = speakerData.find((item) => item.id === Number(id));

  // Handle case where product is not found
  if (!product) {
    return <h2>Speaker not found!</h2>;
  }

  return (
    <>
      <Navbar />
      <div className="ind-section">
        <div className="ind-image">
          <img src={product.image} alt={product.model} />
        </div>
        <div className="ind-details">
          <h2>{product.brand}</h2>
          <h3>{product.model}</h3>
          <h2>${product.price}</h2>
          <p>{product.description}</p>
          <button onClick={() => addToCart(product)}>Add to Cart</button>
        </div>
      </div>
    </>
  );
};

export default SpeakerSingle;
