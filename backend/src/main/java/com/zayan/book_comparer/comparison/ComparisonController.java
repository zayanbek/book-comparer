package com.zayan.book_comparer.comparison;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comparisons")
public class ComparisonController {

     private final ComparisonService comparisonService;

     public ComparisonController(ComparisonService comparisonService) {
          this.comparisonService = comparisonService;
     }

     @GetMapping
     public ComparisonResultDto compare(@RequestParam String bookA, @RequestParam String bookB) {
          return comparisonService.compare(bookA, bookB);
     }
}
