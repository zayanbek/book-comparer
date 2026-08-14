package com.zayan.book_comparer.analysis;

import java.util.Map;

public class ProbabilityDistribution {

     Map<String, TokenStatistic> distribution;

     public ProbabilityDistribution(Map<String, TokenStatistic> distribution) {
          this.distribution = distribution;
     }

     public double entropy() {
          double sum = 0;

          for(TokenStatistic value : distribution.values()) {
               double probability = value.getProbability();
               sum += probability * this.log2(probability);
          }

          return -1 * sum;
     }







     private static double log2(double x) {
          return Math.log(x) / Math.log(2);
     }

     public Map<String, TokenStatistic> getDistribution() {
          return distribution;
     }

     public void setDistribution(Map<String, TokenStatistic> distribution) {
          this.distribution = distribution;
     }
}
