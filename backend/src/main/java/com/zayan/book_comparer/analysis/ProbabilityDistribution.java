package com.zayan.book_comparer.analysis;

import java.util.Map;

public class ProbabilityDistribution {

     Map<String, TokenStatistic> distribution;
     private static final double EPSILON = 1e-10;

     public ProbabilityDistribution(Map<String, TokenStatistic> distribution) {
          this.distribution = distribution;
     }

     public double entropy() {
          double entropy = 0;

          for(TokenStatistic value : distribution.values()) {
               double probability = value.getProbability();
               entropy += probability * log2(probability);
          }

          return -1 * entropy;
     }

     public double klDivergence(ProbabilityDistribution otherDistribution) {
          double divergence = 0.0;

          for (String token : distribution.keySet()) {

               double p = distribution.get(token).getProbability();
               double q = otherDistribution.getProbability(token);

               if (p < EPSILON) continue;
               if (q < EPSILON) return Double.POSITIVE_INFINITY;

               divergence += p * log2(p / q);
          }

          return divergence;
     }

     public double jsDivergence(ProbabilityDistribution otherDistribution) {


     }

     public double cosineSimilarity(ProbabilityDistribution otherDistribution) {



          return -3.14;
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

     public double getProbability(String token) {
          TokenStatistic statistic = distribution.get(token);

          if (statistic == null) {
               return 0.0;
          }

          return statistic.getProbability();
     }
}
