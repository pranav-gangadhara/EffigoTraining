import React from "react";
import { booksData } from "../data/books"; // Ensure the correct import path
import { Link } from "react-router-dom";

const Books = () => {
  const firstFiveBooks = booksData.slice(0, 5);

  return (
    <>
      <div className="proTitle">
        <h2>Books</h2>
      </div>
      <div className="proSection">
        {firstFiveBooks.map((item) => {
          return (
            <div className="imgBox" key={item.id}>
              <Link to={`/books/${item.id}`}>
                <img className="proImage" src={item.image} alt={item.title} />
              </Link>
            </div>
          );
        })}
      </div>
    </>
  );
};

export default Books;
