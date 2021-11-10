package com.dsi.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "image", nullable = true, columnDefinition = "TEXT")
    private String image;

    @Column(name = "rating", nullable = true, columnDefinition = "integer default 0")
    private Integer rating;

    @Column(name = "time", nullable = false)
    private Long dateTimeMilli;

    @Column(name = "likes", nullable = true, columnDefinition = "integer default 0")
    private Integer likes;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy="review")
    private List<ReviewReply> reviewReplies;

    @OneToOne
    private User user;




}
