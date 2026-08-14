package com.zayan.book_comparer.book;

import org.springframework.stereotype.Service;

@Service
public class BookService {

     private final BookRepository bookRepository;

     public BookService(BookRepository bookRepository) {
          this.bookRepository = bookRepository;
     }

     public BookDto getBook(long id) {
          Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

          return new BookDto(book);

     }
}
