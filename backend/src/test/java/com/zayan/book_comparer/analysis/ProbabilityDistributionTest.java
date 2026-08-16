package com.zayan.book_comparer.analysis;

import com.zayan.book_comparer.book.Book;
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
     void klDivergence_identicalDistributions_shouldBeZero() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          assertEquals(
                  0.0,
                  p.klDivergence(q),
                  1e-9
          );
     }

     @Test
     void klDivergence_tokenMissingFromQ_shouldBeInfinity() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(100, 1.0)
                  )
          );

          assertEquals(
                  Double.POSITIVE_INFINITY,
                  p.klDivergence(q)
          );
     }

     @Test
     void klDivergence_tokenMissingFromP_shouldRemainFinite() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(100, 1.0)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(50, 0.5),
                          "b", new TokenStatistic(50, 0.5)
                  )
          );

          assertFalse(
                  Double.isInfinite(p.klDivergence(q))
          );
     }

     @Test
     void klDivergence_zeroProbabilityInP_shouldBeIgnored() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(100, 1.0),
                          "b", new TokenStatistic(0, 0.0)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(100, 1.0),
                          "b", new TokenStatistic(100, 0.0)
                  )
          );

          assertEquals(
                  0.0,
                  p.klDivergence(q),
                  1e-9
          );
     }

     @Test
     void klDivergence_completelyDifferentVocabularies_shouldBeInfinity() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(100, 1.0)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "b", new TokenStatistic(100, 1.0)
                  )
          );

          assertEquals(
                  Double.POSITIVE_INFINITY,
                  p.klDivergence(q)
          );
     }

     @Test
     void klDivergence_smallProbability_shouldRemainFinite() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(999, 0.999),
                          "b", new TokenStatistic(1, 0.001)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(998, 0.998),
                          "b", new TokenStatistic(2, 0.002)
                  )
          );

          double result = p.klDivergence(q);

          assertTrue(Double.isFinite(result));
          assertTrue(result >= 0.0);
     }

     @Test
     void klDivergence_shouldNeverBeNegative() {

          ProbabilityDistribution p = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(70, 0.7),
                          "b", new TokenStatistic(30, 0.3)
                  )
          );

          ProbabilityDistribution q = new ProbabilityDistribution(
                  Map.of(
                          "a", new TokenStatistic(40, 0.4),
                          "b", new TokenStatistic(60, 0.6)
                  )
          );

          double result = p.klDivergence(q);

          assertTrue(result >= 0.0);
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

     @Test
     void buildDistribution_shouldCountSingleWord() {

          Book book = new Book();
          book.setText("odyssey");

          ProbabilityDistribution distribution =
                  new ProbabilityDistribution(book);

          assertEquals(
                  1.0,
                  distribution.getProbability("odyssey"),
                  1e-10
          );
     }

}