package com.zayan.book_comparer.comparison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComparisonRepository extends JpaRepository<Comparison, Long> {

     Optional<Comparison> findByBookAIdAndBookBId(Long bookAId, Long bookBId);

}