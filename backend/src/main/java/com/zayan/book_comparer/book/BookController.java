package com.zayan.book_comparer.book;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

     @GetMapping("/search")
     public List<BookDto> searchBooks(
             @RequestParam String title,
             @RequestParam Integer page,
             @RequestParam Integer size
     ) {
          return bookService.getBook(title, page, size);
     }

}
