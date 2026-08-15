package com.zayan.book_comparer.analysis;

import com.zayan.book_comparer.book.Book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProbabilityDistribution {

     private final Map<String, TokenStatistic> distribution;
     private static final double EPSILON = 1e-10;

     public ProbabilityDistribution(Map<String, TokenStatistic> distribution) {
          this.distribution = distribution;
     }

     public ProbabilityDistribution(Book book) {
          // Iterate through the book, update distribution as you go
          distribution = buildDistribution(book.getText());
     }

     private static Map<String, TokenStatistic> buildDistribution(String text) {
          Map<String, TokenStatistic> result = new HashMap<>();

          String[] words = text.split("\\s+");
          int length = words.length;

          for(String word : words) {
               if (!word.isEmpty()) {
                    TokenStatistic statistic = result.get(word);

                    if(statistic == null) {
                         statistic = new TokenStatistic(length);
                         result.put(word, statistic);
                    }

                    statistic.increment();
               }
          }

          return result;
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
          double divergence = 0.0;

          // get a set of all unique tokens
          Set<String> tokens = new HashSet<>(distribution.keySet());
          tokens.addAll(otherDistribution.distribution.keySet());

          for (String token : tokens) {

               double p = getProbability(token);
               double q = otherDistribution.getProbability(token);

               double m = (p + q) / 2.0;

               if (p >= EPSILON) divergence += 0.5 * p * log2(p / m);

               if (q >= EPSILON) divergence += 0.5 * q * log2(q / m);

          }

          return divergence;
     }

     public double cosineSimilarity(ProbabilityDistribution otherDistribution) {

          double dotProduct = 0.0;
          double magnitudeA = 0.0;
          double magnitudeB = 0.0;

          Set<String> tokens = new HashSet<>(distribution.keySet());
          tokens.addAll(otherDistribution.distribution.keySet());

          for (String token : tokens) {

               double a = getProbability(token);
               double b = otherDistribution.getProbability(token);

               dotProduct += a * b;
               magnitudeA += a * a;
               magnitudeB += b * b;
          }

          if (magnitudeA < EPSILON || magnitudeB < EPSILON) {
               return 0.0;
          }

          return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
     }

     private static double log2(double x) {
          return Math.log(x) / Math.log(2);
     }

     public double getProbability(String token) {
          TokenStatistic statistic = distribution.get(token);

          if (statistic == null) return 0.0;

          return statistic.getProbability();
     }
}
