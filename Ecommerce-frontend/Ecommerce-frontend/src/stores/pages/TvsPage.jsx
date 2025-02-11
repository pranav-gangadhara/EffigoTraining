import React, { useState } from "react";
import { tvData } from "../data/tv";
import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const TvsPage = () => {
  const [selectedBrands, setSelectedBrands] = useState([]);

  // Handle brand selection
  const handleBrandSelection = (brand) => {
    if (selectedBrands.includes(brand)) {
      setSelectedBrands(selectedBrands.filter((b) => b !== brand));
    } else {
      setSelectedBrands([...selectedBrands, brand]);
    }
  };

  // Filter TVs based on selected brands
  const filteredTvs =
    selectedBrands.length === 0
      ? tvData
      : tvData.filter((tv) => selectedBrands.includes(tv.brand));

  return (
    <>
      <Navbar />
      <div className="fullpage">
        {/* Brand Selection Filters */}
        <div className="pro-selected">
          {Array.from(new Set(tvData.map((tv) => tv.brand))).map((brand) => (
            <div className="pro-input" key={brand}>
              <label>
                <input
                  type="checkbox"
                  checked={selectedBrands.includes(brand)}
                  onChange={() => handleBrandSelection(brand)}
                />
                {brand}
              </label>
            </div>
          ))}
        </div>

        {/* Display Filtered TVs */}
        <div className="pageSection">
          {filteredTvs.map((tv) => (
            <div key={tv.id} className="product-card">
              <Link to={`/tvs/${tv.id}`}>
                <div className="pageImg">
                  <img src={tv.image} alt={tv.model} />
                </div>
              </Link>
              <div className="proModel">
                <strong>{tv.brand}</strong> - {tv.model}
              </div>
              <div className="proPrice">${tv.price}</div>
              <div className="proDescription">{tv.description}</div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default TvsPage;
