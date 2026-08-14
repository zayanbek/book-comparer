package com.zayan.book_comparer.comparison;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="comparisons")
public class Comparison {

     @Id
     private long id;

     @Column(name = "book_a_id")
     private long bookAId;

     @Column(name = "book_b_id")
     private long bookBId;

     @Column(name = "is_complete")
     private boolean isComplete;

     @Column(name = "cosine_similarity")
     private double cosineSimilarity;

     @Column(name = "kl_divergence_a_to_b")
     private double klDivergenceAB;

     @Column(name = "kl_divergence_b_to_a")
     private double klDivergenceBA;

     @Column(name = "js_divergence")
     private double jsDivergence;



}
