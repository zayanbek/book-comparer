package com.zayan.book_comparer.comparison;

public class ComparisonResultDto {

     private String bookA, bookB;
     private Double cosineSimilarity, klDivergenceAB, klDivergenceBA, jsDivergence;

     public ComparisonResultDto() {
     }

     public ComparisonResultDto(String bookA, String bookB, double cosineSimilarity, double klDivergenceAB, double klDivergenceBA, double jsDivergence) {
          this.bookA = bookA;
          this.bookB = bookB;
          this.cosineSimilarity = cosineSimilarity;
          this.klDivergenceAB = klDivergenceAB;
          this.klDivergenceBA = klDivergenceBA;
          this.jsDivergence = jsDivergence;
     }

     public String getBookA() {
          return bookA;
     }

     public void setBookA(String bookA) {
          this.bookA = bookA;
     }

     public String getBookB() {
          return bookB;
     }

     public void setBookB(String bookB) {
          this.bookB = bookB;
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
