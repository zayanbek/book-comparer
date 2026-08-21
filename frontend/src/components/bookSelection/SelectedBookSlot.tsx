import type { BookSearchResult } from "../../api/books";

interface SelectedBookSlotProps {
  book?: BookSearchResult;
  onRemove: (bookId: number) => void;
}

function SelectedBookSlot({ book, onRemove }: SelectedBookSlotProps) {
  if (!book) {
    return (
      <div className="selected-book empty">
        <p>Select a book</p>
      </div>
    );
  }

  return (
    <div className="selected-book">
      <div className="selected-cover">
        <img
          src={`data:image/jpeg;base64,${book.image}`}
          alt={`${book.title} cover`}
        />
      </div>

      <div className="selected-book-info">
        <h3>{book.title}</h3>
        <p>{book.author}</p>
      </div>

      <button
        className="remove-book-button"
        onClick={() => onRemove(book.id)}
        aria-label={`Remove ${book.title}`}
      >
        Remove
      </button>
    </div>
  );
}

export default SelectedBookSlot;
