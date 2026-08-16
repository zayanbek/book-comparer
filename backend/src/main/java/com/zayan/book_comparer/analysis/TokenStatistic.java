package com.zayan.book_comparer.analysis;

public class TokenStatistic {

     private int frequency;
     private double probability;
     private int totalLength;

     public TokenStatistic(int frequency, double probability) {
          this.frequency = frequency;
          this.probability = probability;
     }

     public TokenStatistic(int totalLength) {
          this.frequency = 0;
          this.probability = 0;
          this.totalLength = totalLength;
     }

     public void increment() {
          frequency += 1;
          probability = (double) frequency / totalLength;
     }

     public int getFrequency() {
          return frequency;
     }

     public void setFrequency(int frequency) {
          this.frequency = frequency;
     }

     public double getProbability() {
          return probability;
     }

     public void setProbability(double probability) {
          this.probability = probability;
     }
}
