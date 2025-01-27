package com.roomfit.be.survey.application.dto;

import com.roomfit.be.survey.domain.Questionnaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuestionnaireDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        private String title;
        private String description;
        private List<QuestionDTO.Request> questions;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private List<QuestionDTO.Response> questions;

        public static Response of(Questionnaire questionnaire) {
            return Response.builder()
                    .id(questionnaire.getId())
                    .title(questionnaire.getTitle())
                    .description(questionnaire.getDescription())
                    .questions(
                            questionnaire.getQuestions()
                                    .stream()
                                    .map(QuestionDTO.Response::of)
                                    .toList()
                    )
                    .build();

        }
        public static Response ofWithReplies(Questionnaire questionnaire) {
            return Response.builder()
                    .id(questionnaire.getId())
                    .title(questionnaire.getTitle())
                    .description(questionnaire.getDescription())
                    .questions(
                            questionnaire.getQuestions()
                                    .stream()
                                    .map(QuestionDTO.Response::ofWithReplies)
                                    .toList()
                    )
                    .build();

        }

        public void addQuestions(List<QuestionDTO.Response> questions) {
            this.questions = questions;
        }
    }
}
