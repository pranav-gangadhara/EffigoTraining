import React from "react";
import { booksData } from "../data/books";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";
import { useCart } from "../context/CartContext";

const BookSingle = () => {
  const { id } = useParams();
  const { addToCart } = useCart();

  const product = booksData.find((item) => item.id === id);

  if (!product) {
    return (
      <>
        <Navbar />
        <div className="ind-section">
          <h2>Book Not Found</h2>
        </div>
      </>
    );
  }

  return (
    <>
      <Navbar />
      <div className="ind-section">
        <div className="ind-image">
          <img src={product.image} alt={product.title} />
        </div>
        <div className="ind-details space">
          <div className="ind-title">
            <h2>{product.title}</h2>
          </div>
          <div className="ind-author space">
            <h3>by {product.author}</h3>
          </div>
          <div className="ind-price space">
            <h2>${product.price}</h2>
          </div>
          <div className="ind-category space">
            <p>Category: {product.category}</p>
          </div>
          <div className="ind-desc space">
            <p>{product.description}</p>
          </div>
          <button onClick={() => addToCart(product)}>Add to Cart</button>
        </div>
      </div>
    </>
  );
};

export default BookSingle;
