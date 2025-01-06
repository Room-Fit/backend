package com.roomfit.be.survey.domain;

import jakarta.persistence.*;

@Entity(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "reply_label")
    String label;
    @Column(name = "reply_value")
    String value;
}
