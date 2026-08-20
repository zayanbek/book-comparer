import { useEffect, useRef, useState } from "react";
import BookCard from "./bookCard/BookCard";
import { searchBooks, type BookSearchResult } from "../../api/books";

interface BookCarouselProps {
  search: string;
  selectedBooks: BookSearchResult[];
  onToggleBook: (book: BookSearchResult) => void;
}

const CARD_WIDTH = 155;
const CARD_GAP = 18;

function BookCarousel({
  search,
  selectedBooks,
  onToggleBook,
}: BookCarouselProps) {
  const carouselRef = useRef<HTMLDivElement>(null);

  const [books, setBooks] = useState<BookSearchResult[]>([]);
  const [cardCount, setCardCount] = useState(1);
  const [loading, setLoading] = useState(false);

  /*
   * Calculate how many BookCards can fit
   * inside the available carousel width.
   */
  useEffect(() => {
    const calculateCardCount = () => {
      if (!carouselRef.current) {
        return;
      }

      const width = carouselRef.current.clientWidth;

      const count = Math.max(
        1,
        Math.floor((width + CARD_GAP) / (CARD_WIDTH + CARD_GAP)),
      );

      setCardCount(count);
    };

    calculateCardCount();

    const resizeObserver = new ResizeObserver(calculateCardCount);

    if (carouselRef.current) {
      resizeObserver.observe(carouselRef.current);
    }

    return () => {
      resizeObserver.disconnect();
    };
  }, []);

  /*
   * Request the number of books that can
   * actually fit on the screen.
   */
  useEffect(() => {
    let cancelled = false;

    const loadBooks = async () => {
      setLoading(true);

      try {
        const results = await searchBooks(search, 0, cardCount);

        if (!cancelled) {
          setBooks(results);
        }
      } catch (error) {
        console.error("Failed to load books:", error);

        if (!cancelled) {
          setBooks([]);
        }
      } finally {
        if (!cancelled) {
          setLoading(false);
        }
      }
    };

    loadBooks();

    return () => {
      cancelled = true;
    };
  }, [search, cardCount]);

  return (
    <div ref={carouselRef} className="book-carousel">
      {loading ? (
        <div className="carousel-message">Loading books...</div>
      ) : books.length === 0 ? (
        <div className="carousel-message">No books found.</div>
      ) : (
        books.map((book) => (
          <BookCard
            key={book.id}
            book={book}
            selected={selectedBooks.some((selected) => selected.id === book.id)}
            onClick={() => onToggleBook(book)}
          />
        ))
      )}
    </div>
  );
}

export default BookCarousel;
