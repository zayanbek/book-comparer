package com.zayan.book_comparer.book;

import com.zayan.book_comparer.exception.BookNotFoundException;
import com.zayan.book_comparer.exception.BookTitleNotSpecificException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

     private final BookRepository bookRepository;

     public BookService(BookRepository bookRepository) {
          this.bookRepository = bookRepository;
     }

     public BookDto getBook(long id) {
          Book book = bookRepository.findById(id)
                  .orElseThrow(() ->
                          new BookNotFoundException(
                                  "Book with id " + id + " not found"
                          ));

          return new BookDto(book);
     }

     public Book getBook(String title) {

          Optional<Book> exactMatch = bookRepository.findByTitleIgnoreCase(title);

          if (exactMatch.isPresent()) {
               return exactMatch.get();
          }

          List<Book> partialMatches =
                  bookRepository.findByTitleContainingIgnoreCase(title);

          if (partialMatches.isEmpty()) {
               throw new BookNotFoundException(
                       "No book found with title: " + title
               );
          }

          if (partialMatches.size() > 1) {
               throw new BookTitleNotSpecificException(
                       "Title '" + title + "' is not specific enough"
               );
          }

          return partialMatches.get(0);
     }
}
