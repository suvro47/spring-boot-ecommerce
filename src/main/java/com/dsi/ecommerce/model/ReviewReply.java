package com.dsi.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="review_replies")
public class ReviewReply {

    @Id
    @SequenceGenerator(name = "comment_id_sequence", sequenceName = "comment_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "content", nullable = true, columnDefinition = "TEXT")
    private String content;

    @Column(name = "time", nullable = false)
    private Long dateTimeMilli;

    @Column(name = "likes", nullable = true, columnDefinition = "integer default 0")
    private Integer likes;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User user;

}
