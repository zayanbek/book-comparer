package com.zayan.book_comparer.book;

public class BookSearchDto {

     private Long id;
     private String title, author;
     private byte[] image;

     public BookSearchDto() {
     }

     public BookSearchDto(Long id, String title, String author, byte[] image) {
          this.id = id;
          this.title = title;
          this.author = author;
          this.image = image;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public String getAuthor() {
          return author;
     }

     public void setAuthor(String author) {
          this.author = author;
     }

     public byte[] getImage() {
          return image;
     }

     public void setImage(byte[] image) {
          this.image = image;
     }
}
