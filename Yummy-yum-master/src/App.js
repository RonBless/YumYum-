import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Moon from '../src/pages/Menues/Moon/src/moon_Menu';
import Resturants from '../src/components/availableResturants';
import Arcaffe from '../src/pages/Menues/Arcaffe/src/arcaffe_menu';
import Giraffe from '../src/pages/Menues/Giraffe/src/giraffe_menu';
import Gluteria from '../src/pages/Menues/Gluteria/src/gluteria_menu';
import J17 from '../src/pages/Menues/J17/src/j17_menu';
import Vivino from '../src/pages/Menues/Vivino/src/vivino_menu';
import TheGarage from '../src/pages/Menues/The Garage/src/thegarage_menu';
import OnlyMeat from '../src/pages/Menues/Only Meat/src/onlymeat_menu';
import NonoMimi from '../src/pages/Menues/Nono Mimi/src/nonomimi_menu';

import { BrowserRouter } from 'react-router-dom';
import Login from './login-page/login-page';

const App = () => {
  return (
    <BrowserRouter>
      <Routes >
        <Route path="/" element={<Login/>}/>
        <Route path="/resturaunts/:id" element={<Resturants />} />
        <Route path="/resturaunts/:id/moon" element={<Moon />} />
        <Route path="/resturaunts/:id/arcaffe" element={<Arcaffe />} />
        <Route path="/resturaunts/:id/giraffe" element={<Giraffe />} />
        <Route path="/resturaunts/:id/gluteria" element={<Gluteria />} />
        <Route path="/resturaunts/:id/j17" element={<J17 />} />
        <Route path="/resturaunts/:id/vivino" element={<Vivino />} />
        <Route path="/resturaunts/:id/thegarage" element={<TheGarage />} />
        <Route path="/resturaunts/:id/onlymeat" element={<OnlyMeat />} />
        <Route path="/resturaunts/:id/nonomimi" element={<NonoMimi />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;