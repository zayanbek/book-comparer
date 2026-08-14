package com.zayan.book_comparer.analysis;

public class TokenStatistic {

     private int frequency;
     private double probability;

     public TokenStatistic(int frequency, double probability) {
          this.frequency = frequency;
          this.probability = probability;
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
