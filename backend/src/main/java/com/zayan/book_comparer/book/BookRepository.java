package com.zayan.book_comparer.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

     Optional<Book> findByTitle(String title);
     List<Book> findByTitleContainingIgnoreCase(String title);
     Optional<Book> findByTitleIgnoreCase(String title);
}
