package com.zayan.book_comparer.analysis;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProbabilityDistributionTest {

     @Test
     void identicalDistributions_shouldHaveCosineSimilarityOfOne() {

          ProbabilityDistribution a = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          ProbabilityDistribution b = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          assertEquals(
                  1.0,
                  a.cosineSimilarity(b),
                  1e-10
          );
     }

     @Test
     void klDivergence_shouldCalculateExpectedValue() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(25, 0.25),
                          "b", new TokenStatistic(75, 0.75)
                  )
          );

          double expected = 0.2075187496;

          assertEquals(
                  expected,
                  p.klDivergence(q),
                  1e-9
          );
     }

     @Test
     void jsDivergence_shouldBeSymmetric() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(25, 0.25),
                          "b", new TokenStatistic(75, 0.75)
                  )
          );

          double pToQ = p.jsDivergence(q);
          double qToP = q.jsDivergence(p);

          assertEquals(
                  pToQ,
                  qToP,
                  1e-10
          );
     }

}