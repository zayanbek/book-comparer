package com.zayan.book_comparer.comparison;

import com.zayan.book_comparer.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name="comparisons")
public class Comparison {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "book_a_id", nullable = false)
     private Book bookA;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "book_b_id", nullable = false)
     private Book bookB;

     @Column(name = "is_complete")
     private boolean isComplete;

     @Column(name = "cosine_similarity")
     private double cosineSimilarity;

     @Column(name = "kl_divergence_a_to_b")
     private double klDivergenceAB;

     @Column(name = "kl_divergence_b_to_a")
     private double klDivergenceBA;

     @Column(name = "js_divergence")
     private double jsDivergence;

     public Comparison() {
     }

     public Comparison(Book bookA, Book bookB) {
          this.bookA = bookA;
          this.bookB = bookB;
     }

     public Comparison(long id, Book bookA, Book bookB, boolean isComplete, double cosineSimilarity, double klDivergenceAB, double klDivergenceBA, double jsDivergence) {
          this.id = id;
          this.bookA = bookA;
          this.bookB = bookB;
          this.isComplete = isComplete;
          this.cosineSimilarity = cosineSimilarity;
          this.klDivergenceAB = klDivergenceAB;
          this.klDivergenceBA = klDivergenceBA;
          this.jsDivergence = jsDivergence;
     }

     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public Book getBookA() {
          return bookA;
     }

     public void setBookA(Book bookA) {
          this.bookA = bookA;
     }

     public Book getBookB() {
          return bookB;
     }

     public void setBookB(Book bookB) {
          this.bookB = bookB;
     }

     public boolean isComplete() {
          return isComplete;
     }

     public void setComplete(boolean complete) {
          isComplete = complete;
     }

     public double getCosineSimilarity() {
          return cosineSimilarity;
     }

     public void setCosineSimilarity(double cosineSimilarity) {
          this.cosineSimilarity = cosineSimilarity;
     }

     public double getKlDivergenceAB() {
          return klDivergenceAB;
     }

     public void setKlDivergenceAB(double klDivergenceAB) {
          this.klDivergenceAB = klDivergenceAB;
     }

     public double getKlDivergenceBA() {
          return klDivergenceBA;
     }

     public void setKlDivergenceBA(double klDivergenceBA) {
          this.klDivergenceBA = klDivergenceBA;
     }

     public double getJsDivergence() {
          return jsDivergence;
     }

     public void setJsDivergence(double jsDivergence) {
          this.jsDivergence = jsDivergence;
     }
}
