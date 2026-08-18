import "./App.css";

import { useState } from "react";

import Header from "./components/header/Header";
import SearchBar from "./components/searchBar/SearchBar";
import Footer from "./components/footer/Footer";
import Carousel from "./components/carousel/Carousel";
import Selection from "./components/selection/Selection";
import Metrics from "./components/metrics/Metrics";

export default function App() {
  const [search, setSearch] = useState("");

  return (
    <div className="app">
      <Header />

      <main className="main">
        <SearchBar value={search} onChange={setSearch} />
        <Carousel />
        <Selection />
        <Metrics />
      </main>

      <Footer />
    </div>
  );
}
