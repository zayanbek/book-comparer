package com.zayan.book_comparer.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

     private final BookService bookService;

     public BookController(BookService bookService) {
          this.bookService = bookService;
     }

     @GetMapping
     public BookDto getBook(@RequestParam(required = true) Long id) {
          return bookService.getBook(id);
     }
}
