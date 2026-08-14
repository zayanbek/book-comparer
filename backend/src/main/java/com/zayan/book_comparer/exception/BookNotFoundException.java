package com.zayan.book_comparer.exception;

public class BookNotFoundException extends RuntimeException {
     public BookNotFoundException(String message) {
          super(message);
     }
}
