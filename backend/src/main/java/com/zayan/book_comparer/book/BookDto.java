package com.zayan.book_comparer.book;

public class BookDto {

     private Long id;
     private String title, author, text;
     private byte[] image;

     public BookDto() {}

     public BookDto(Book book) {
          this.id = book.getId();
          this.title = book.getTitle();
          this.author = book.getAuthor();
          this.text = book.getText();
          this.image = book.getImage();
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

     public String getText() {
          return text;
     }

     public void setText(String text) {
          this.text = text;
     }

     public byte[] getImage() {
          return image;
     }

     public void setImage(byte[] image) {
          this.image = image;
     }
}
