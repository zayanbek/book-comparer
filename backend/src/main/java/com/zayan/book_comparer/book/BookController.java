package com.zayan.book_comparer.book;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

     private final BookService bookService;

     public BookController(BookService bookService) {
          this.bookService = bookService;
     }

     @GetMapping("/{id}")
     public BookDto getBookById(@PathVariable Long id) {
          return bookService.getBook(id);
     }

     @GetMapping
     public BookDto getBookByTitle(@RequestParam String title) {
          return new BookDto(bookService.getBook(title));
     }
}
