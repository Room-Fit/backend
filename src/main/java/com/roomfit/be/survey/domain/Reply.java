package com.roomfit.be.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "replies")
@Getter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "reply_label")
    String label;
    @Column(name = "reply_value")
    String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id")
    Question question;

    public Reply(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * user과도 관계 매핑 필요.
     */

    public void addQuestion(Question question){
        this.question = question;
    }
}
