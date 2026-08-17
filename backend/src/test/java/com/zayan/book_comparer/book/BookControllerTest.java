package com.zayan.book_comparer.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

     @Autowired
     private MockMvc mockMvc;

     @MockitoBean
     private BookService bookService;

     @Test
     void getBookById_shouldReturnCorrectBook() throws Exception {

          BookDto book = new BookDto();
          book.setId(1L);
          book.setTitle("The Odyssey");
          book.setAuthor("Homer");

          when(bookService.getBook(1L))
                  .thenReturn(book);

          mockMvc.perform(get("/books/1"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.title").value("The Odyssey"))
                  .andExpect(jsonPath("$.author").value("Homer"));

          verify(bookService).getBook(1L);
     }

     @Test
     void getBookByTitle_shouldReturnCorrectBook() throws Exception {

          Book book = new Book();
          book.setId(1L);
          book.setTitle("The Odyssey");
          book.setAuthor("Homer");

          when(bookService.getBook("The Odyssey"))
                  .thenReturn(book);

          mockMvc.perform(
                          get("/books")
                                  .param("title", "The Odyssey")
                  )
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.title").value("The Odyssey"))
                  .andExpect(jsonPath("$.author").value("Homer"));

          verify(bookService).getBook("The Odyssey");
     }
}