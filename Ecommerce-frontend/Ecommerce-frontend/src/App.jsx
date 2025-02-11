import React from 'react';
import { Routes, Route } from 'react-router-dom';

import './App.css';
import LandingPage from './stores/pages/LandingPage';
import KitchenPage from './stores/pages/KitchenPage';
import MobilePage from './stores/pages/MobilePage';
import CompPage from './stores/pages/CompPage';
import WatchPage from './stores/pages/WatchPage';
import MenPage from './stores/pages/MenPage';
import WomanPage from './stores/pages/WomanPage';
import FurniturePage from './stores/pages/FurniturePage';
import AcPage from './stores/pages/AcPage';
import FridgePage from './stores/pages/FridgePage';
import BookPage from './stores/pages/BookPage';
import SpeakersPage from './stores/pages/SpeakersPage';
import TVsPage from './stores/pages/TvsPage'; // Corrected import name

import UserCart from './stores/UserCart';
import MobileSingle from './stores/singles/MobileSingle';
import ComputerSingle from './stores/singles/ComputerSingle';
import FurnitureSingle from './stores/singles/FurnitureSingle';
import KitchenSingle from './stores/singles/KitchenSingle';
import AcSingle from './stores/singles/AcSingle';
import MenSingle from './stores/singles/MenSingle';
import WatchSingle from './stores/singles/WatchSingle';
import WomanSingle from './stores/singles/WomanSingle';
import FridgeSingle from './stores/singles/FridgeSingle';
import BookSingle from './stores/singles/BookSingle';
import SpeakerSingle from './stores/singles/SpeakerSingle';
import TvSingle from './stores/singles/TvSingle';


const App = () => {
  return (
    <div>
      <Routes>
        {/* Main Pages */}
        <Route path="/" element={<LandingPage />} />
        <Route path="/kitchen" element={<KitchenPage />} />
        <Route path="/mobiles" element={<MobilePage />} />
        <Route path="/computers" element={<CompPage />} />
        <Route path="/watch" element={<WatchPage />} />
        <Route path="/fridge" element={<FridgePage />} />
        <Route path="/men" element={<MenPage />} />
        <Route path="/woman" element={<WomanPage />} />
        <Route path="/furniture" element={<FurniturePage />} />
        <Route path="/ac" element={<AcPage />} />
        <Route path="/books" element={<BookPage />} />
        <Route path="/speakers" element={<SpeakersPage />} />
        <Route path="/tvs" element={<TVsPage />} />

        {/* Single Product Pages */}
        <Route path="/mobiles/:id" element={<MobileSingle />} />
        <Route path="/cart" element={<UserCart />} />
        <Route path="/ac/:id" element={<AcSingle />} />
        <Route path="/computers/:id" element={<ComputerSingle />} />
        <Route path="/furniture/:id" element={<FurnitureSingle />} />
        <Route path="/kitchen/:id" element={<KitchenSingle />} />
        <Route path="/men/:id" element={<MenSingle />} />
        <Route path="/watch/:id" element={<WatchSingle />} />
        <Route path="/woman/:id" element={<WomanSingle />} />
        <Route path="/fridge/:id" element={<FridgeSingle />} />
        <Route path="/books/:id" element={<BookSingle />} />
        <Route path="/speakers/:id" element={<SpeakerSingle />} />
        <Route path="/tvs/:id" element={<TvSingle />} />

        
      </Routes>
    </div>
  );
};

export default App;
