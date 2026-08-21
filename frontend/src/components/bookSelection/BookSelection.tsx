import type { BookSearchResult } from "../../api/books";
import "./BookSelection.css";

interface BookSelectionProps {
  selectedBooks: BookSearchResult[];
}

function BookSelection({ selectedBooks }: BookSelectionProps) {
  return (
    <section className="selected-books-section">
      <h2>Selected Volumes</h2>

      <div className="selected-books">
        <SelectedBookSlot book={selectedBooks[0]} />
        <SelectedBookSlot book={selectedBooks[1]} />
      </div>
    </section>
  );
}

interface SelectedBookSlotProps {
  book?: BookSearchResult;
}

function SelectedBookSlot({ book }: SelectedBookSlotProps) {
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
    </div>
  );
}

export default BookSelection;
