import type { BookSearchResult } from "../../api/books";
import "./BookSelection.css";

import SelectedBookSlot from "./SelectedBookSlot";

interface BookSelectionProps {
  selectedBooks: BookSearchResult[];
  onRemove: (bookId: number) => void;
}

function BookSelection({ selectedBooks, onRemove }: BookSelectionProps) {
  return (
    <section className="selected-books-section">
      <h2>Selected Volumes</h2>

      <div className="selected-books">
        <SelectedBookSlot book={selectedBooks[0]} onRemove={onRemove} />

        <SelectedBookSlot book={selectedBooks[1]} onRemove={onRemove} />
      </div>
    </section>
  );
}

export default BookSelection;
