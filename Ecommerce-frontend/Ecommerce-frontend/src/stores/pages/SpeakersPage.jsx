import React, { useState } from "react";
import { speakerData } from "../data/speaker";
import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const SpeakersPage = () => {
  const [selectedBrands, setSelectedBrands] = useState([]);

  const handleBrandFilter = (brand) => {
    if (selectedBrands.includes(brand)) {
      setSelectedBrands(selectedBrands.filter((item) => item !== brand));
    } else {
      setSelectedBrands([...selectedBrands, brand]);
    }
  };

  const filteredSpeakers =
    selectedBrands.length === 0
      ? speakerData
      : speakerData.filter((item) => selectedBrands.includes(item.brand));

  return (
    <>
      <Navbar />
      <div className="fullpage">
        <div className="pro-selected">
          {speakerData.map((speaker) => (
            <div className="pro-input" key={speaker.brand}>
              <label>
                <input
                  type="checkbox"
                  checked={selectedBrands.includes(speaker.brand)}
                  onChange={() => handleBrandFilter(speaker.brand)}
                />
                {speaker.brand}
              </label>
            </div>
          ))}
        </div>

        <div className="pageSection">
          {filteredSpeakers.map((item) => (
            <div key={item.model}>
              <Link to={`/speakers/${item.id}`}>
                <div className="pageImg">
                  <img src={item.image} alt={item.model} />
                </div>
              </Link>
              <div className="proModel">
                {item.brand}, {item.model}
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default SpeakersPage;
