import "./BookCard.css";

import type { BookSearchResult } from "../../../api/books";

interface BookCardProps {
  book: BookSearchResult;
  selected: boolean;
  onClick: () => void;
}

function BookCard({ book, selected, onClick }: BookCardProps) {
  return (
    <button
      type="button"
      className={`book-card ${selected ? "selected" : ""}`}
      onClick={onClick}
    >
      <div className="cover-wrapper">
        <img
          src={`data:image/jpeg;base64,${book.image}`}
          alt={`${book.title} cover`}
        />

        {selected && <span className="selected-badge">✓</span>}
      </div>

      <div className="book-info">
        <h3>{book.title}</h3>
        <p>{book.author}</p>
      </div>
    </button>
  );
}

export default BookCard;
