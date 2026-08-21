import "./App.css";

import { useState } from "react";

import Header from "./components/header/Header";
import SearchBar from "./components/searchBar/SearchBar";
import Footer from "./components/footer/Footer";
import BookCarousel from "./components/carousel/BookCarousel";
import Selection from "./components/bookSelection/BookSelection";
import Metrics from "./components/metrics/Metrics";
import type { BookSearchResult } from "./api/books";
import BookSelection from "./components/bookSelection/BookSelection";

export default function App() {
  const [search, setSearch] = useState("");
  const [selectedBooks, setSelectedBooks] = useState<BookSearchResult[]>([]);

  const toggleBook = (book: BookSearchResult) => {
    setSelectedBooks((current) => {
      // If already selected, remove it
      if (current.some((selected) => selected.id === book.id)) {
        return current.filter((selected) => selected.id !== book.id);
      }

      // Don't allow more than two books
      if (current.length >= 2) {
        return current;
      }
      // Add book
      return [...current, book];
    });
  };

  return (
    <div className="app">
      <Header />

      <main className="main">
        <SearchBar value={search} onChange={setSearch} />
        <BookCarousel
          search={search}
          selectedBooks={selectedBooks}
          onToggleBook={toggleBook}
        />
        <BookSelection selectedBooks={selectedBooks} />
        <Metrics />
      </main>

      <Footer />
    </div>
  );
}
