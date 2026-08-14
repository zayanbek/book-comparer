package com.zayan.book_comparer.book;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {

     @Id
     private long id;

     private String title;

     private String author;

     @Column(columnDefinition = "TEXT")
     private String text;

     // @Lob
     @Column(columnDefinition = "BYTEA")
     private byte[] image;

     public Book() {
     }

     public Book(long id, String title, String author, String text, byte[] image) {
          this.id = id;
          this.title = title;
          this.author = author;
          this.text = text;
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
