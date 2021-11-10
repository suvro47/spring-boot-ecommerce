package com.dsi.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="reviews")
public class Review {

    @Id
    @SequenceGenerator(name = "review_id_sequence", sequenceName = "review_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "content", nullable = true, columnDefinition = "TEXT")
    private String content;

    @Column(name = "rating", nullable = true, columnDefinition = "integer default 0")
    private Integer rating;

    @Column(name = "time", nullable = false)
    private Long dateTimeMilli;

    @Column(name = "likes", nullable = true, columnDefinition = "integer default 0")
    private Integer likes;

    @ManyToOne
    private Product product;

//    @OneToMany(mappedBy="review")
//    private List<Comment> comments;

//    @OneToOne
//    private User user;




}
