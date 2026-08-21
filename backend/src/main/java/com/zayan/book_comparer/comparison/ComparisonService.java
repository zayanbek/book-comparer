package com.zayan.book_comparer.comparison;

import com.zayan.book_comparer.analysis.ProbabilityDistribution;
import com.zayan.book_comparer.book.Book;
import com.zayan.book_comparer.book.BookService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ComparisonService {

     private final ComparisonRepository comparisonRepository;
     private final BookService bookService;

     public ComparisonService(ComparisonRepository comparisonRepository, BookService bookService) {
          this.comparisonRepository = comparisonRepository;
          this.bookService = bookService;
     }

     @Transactional
     public ComparisonResultDto compare(Long bookAId, Long bookBId) {

          Book requestedBookA = bookService.getBook(bookAId);
          Book requestedBookB = bookService.getBook(bookBId);

          // Don't allow comparing a book with itself
          if (bookAId.equals(bookBId)) {
               throw new IllegalArgumentException(
                       "Cannot compare a book with itself"
               );
          }

          // 2. Determine whether the request is reversed
          boolean reversed = bookAId > bookBId;

          // 3. Store the smaller ID as book_a
          Long storedIdA = Math.min(bookAId, bookBId);
          Long storedIdB = Math.max(bookAId, bookBId);

          // 4. Look for an existing comparison
          Comparison comparison = comparisonRepository
                  .findByBookAIdAndBookBId(storedIdA, storedIdB)
                  .orElse(null);

          // 5. If it doesn't exist, create it
          if (comparison == null) {

               Book storedBookA = reversed ? requestedBookB : requestedBookA;
               Book storedBookB = reversed ? requestedBookA : requestedBookB;

               comparison = new Comparison(storedBookA, storedBookB);

               calculateMetrics(comparison);

               comparison.setComplete(true);

               comparison = comparisonRepository.save(comparison);
          }

          // 6. If it exists but is incomplete, recalculate it
          else if (!comparison.isComplete()) {

               calculateMetrics(comparison);

               comparison.setComplete(true);

               comparison = comparisonRepository.save(comparison);
          }

          // 7. Return the result in the order requested by the user
          return createResponse(
                  comparison,
                  requestedBookA,
                  requestedBookB,
                  reversed
          );
     }

     private void calculateMetrics(Comparison comparison) {

          ProbabilityDistribution distributionA = new ProbabilityDistribution(comparison.getBookA());
          ProbabilityDistribution distributionB = new ProbabilityDistribution(comparison.getBookB());

          double cosine = distributionA.cosineSimilarity(distributionB);
          double klAToB = distributionA.klDivergence(distributionB);
          double klBToA = distributionB.klDivergence(distributionA);
          double js = distributionA.jsDivergence(distributionB);

          comparison.setCosineSimilarity(cosine);
          comparison.setKlDivergenceAB(klAToB);
          comparison.setKlDivergenceBA(klBToA);
          comparison.setJsDivergence(js);
     }

     private ComparisonResultDto createResponse(
             Comparison comparison,
             Book requestedBookA,
             Book requestedBookB,
             boolean reversed) {

          if (!reversed) {
               return new ComparisonResultDto(
                       requestedBookA.getTitle(),
                       requestedBookB.getTitle(),
                       comparison.getCosineSimilarity(),
                       comparison.getKlDivergenceAB(),
                       comparison.getKlDivergenceBA(),
                       comparison.getJsDivergence()
               );
          }

          // KL divergence is directional, so swap the two values
          return new ComparisonResultDto(
                  requestedBookA.getTitle(),
                  requestedBookB.getTitle(),
                  comparison.getCosineSimilarity(),
                  comparison.getKlDivergenceBA(),
                  comparison.getKlDivergenceAB(),
                  comparison.getJsDivergence()
          );
     }
}
