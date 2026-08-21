package com.zayan.book_comparer.book;

import com.zayan.book_comparer.exception.BookNotFoundException;
import com.zayan.book_comparer.exception.BookTitleNotSpecificException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

     @Mock
     private BookRepository bookRepository;

     @InjectMocks
     private BookService bookService;

     @Test
     void getBook_Id_shouldReturnCorrectBook() {

          // Arrange
          Book book = new Book();
          book.setId(1L);
          book.setTitle("Test Book");
          book.setAuthor("Test Author");

          when(bookRepository.findById(1L))
                  .thenReturn(Optional.of(book));

          // Act
          Book result = bookService.getBook(1L);

          // Assert
          assertEquals(1L, result.getId());
          assertEquals("Test Book", result.getTitle());
          assertEquals("Test Author", result.getAuthor());

          verify(bookRepository).findById(1L);
     }

     @Test
     void getBook_Id_shouldThrowExceptionWhenIdNotFound() {

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

     @Test
     void getBook_odyssey_shouldReturnPartialMatch() {

          Book odyssey = new Book();
          odyssey.setId(1L);
          odyssey.setTitle("The Odyssey");
          odyssey.setAuthor("Homer");

          when(bookRepository.findByTitleIgnoreCase("odyssey"))
                  .thenReturn(Optional.of(odyssey));

          Book result = bookService.getBook("odyssey");

          assertEquals(odyssey, result);

          verify(bookRepository).findByTitleIgnoreCase("odyssey");
          verify(bookRepository, never())
                  .findByTitleContainingIgnoreCase(anyString());
     }

     @Test
     void getBook_iliad_shouldReturnPartialMatch() {

          Book iliad = new Book();
          iliad.setId(2L);
          iliad.setTitle("The Iliad");
          iliad.setAuthor("Homer");

          when(bookRepository.findByTitleIgnoreCase("iliad"))
                  .thenReturn(Optional.empty());

          when(bookRepository.findByTitleContainingIgnoreCase("iliad"))
                  .thenReturn(List.of(iliad));

          Book result = bookService.getBook("iliad");

          assertEquals(iliad, result);

          verify(bookRepository).findByTitleIgnoreCase("iliad");
          verify(bookRepository).findByTitleContainingIgnoreCase("iliad");
     }

     @Test
     void getBook_the_shouldThrowNotSpecificEnoughException() {

          Book odyssey = new Book();
          odyssey.setTitle("The Odyssey");

          Book iliad = new Book();
          iliad.setTitle("The Iliad");

          when(bookRepository.findByTitleIgnoreCase("the"))
                  .thenReturn(Optional.empty());

          when(bookRepository.findByTitleContainingIgnoreCase("the"))
                  .thenReturn(List.of(odyssey, iliad));

          assertThrows(
                  BookTitleNotSpecificException.class,
                  () -> bookService.getBook("the")
          );

          verify(bookRepository).findByTitleIgnoreCase("the");
          verify(bookRepository).findByTitleContainingIgnoreCase("the");
     }

}