import "./App.css";

import { useState } from "react";

import Header from "./components/header/Header";
import SearchBar from "./components/searchBar/SearchBar";
import Footer from "./components/footer/Footer";

export default function App() {
  const [search, setSearch] = useState("");

  return (
    <div className="app">
      <Header />

      <main className="main">
        <SearchBar value={search} onChange={setSearch} />
        {/*
        
        <Carousel />
        <Comparison />
        <Metrics />

        */}
      </main>

      <Footer />
    </div>
  );
}
