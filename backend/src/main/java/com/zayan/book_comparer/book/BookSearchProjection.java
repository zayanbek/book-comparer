package com.zayan.book_comparer.book;

public interface BookSearchProjection {
     Long getId();
     String getTitle();
     String getAuthor();
     byte[] getImage();
}
