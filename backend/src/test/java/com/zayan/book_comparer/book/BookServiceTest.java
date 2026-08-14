package com.zayan.book_comparer.book;

import com.zayan.book_comparer.exception.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

     @Mock
     private BookRepository bookRepository;

     @InjectMocks
     private BookService bookService;

     @Test
     void getBook_shouldReturnCorrectBook() {

          // Arrange
          Book book = new Book();
          book.setId(1L);
          book.setTitle("Test Book");
          book.setAuthor("Test Author");

          when(bookRepository.findById(1L))
                  .thenReturn(Optional.of(book));

          // Act
          BookDto result = bookService.getBook(1L);

          // Assert
          assertEquals(1L, result.getId());
          assertEquals("Test Book", result.getTitle());
          assertEquals("Test Author", result.getAuthor());

          verify(bookRepository).findById(1L);
     }

     @Test
     void getBook_shouldThrowExceptionWhenIdNotFound() {

          // Arrange
          when(bookRepository.findById(1L))
                  .thenReturn(Optional.empty());

          // Act & Assert
          BookNotFoundException exception = assertThrows(
                  BookNotFoundException.class,
                  () -> bookService.getBook(1L)
          );

          assertEquals("Book with id 1 not found", exception.getMessage());

          verify(bookRepository).findById(1L);
     }
}