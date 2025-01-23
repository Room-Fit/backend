package com.roomfit.be.survey.application.dto;

import com.roomfit.be.survey.domain.Option;
import com.roomfit.be.survey.domain.Question;
import com.roomfit.be.survey.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuestionDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String title;

        private QuestionType type;
        private String optionDelimiter;
        private List<OptionDTO.Request> options;

        public Question toEntity() {
            return switch (type) {
                case SLIDER -> Question.createSlider(title, optionDelimiter, toOptionEntities());
                case DOUBLE_SLIDER -> Question.createDoubleSlider(title, optionDelimiter, toOptionEntities());
                case SELECTOR -> Question.createSelector(title, optionDelimiter, toOptionEntities());
                case CHECKBOX -> Question.createCheckbox(title, optionDelimiter, toOptionEntities());
                default -> throw new IllegalArgumentException("Unsupported question type: " + type);
            };
        }

        private List<Option> toOptionEntities() {
            return options.stream()
                    .map(OptionDTO.Request::toEntity)
                    .toList();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private String type;
        private String optionDelimiter;
        private List<OptionDTO.Response> options;
        public static Response of(Question question){
            return Response.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .type(question.getType().name())
                    .optionDelimiter(question.getOptionDelimiter())
                    .options(question.getOptions().stream()
                            .map(OptionDTO.Response::of)
                            .toList()
                    )
                    .build();
        }
        public static Response ofWithReplies(Question question){
            return Response.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .type(question.getType().name())
                    .optionDelimiter(question.getOptionDelimiter())
                    .options(question.getReplies().stream()
                            .map(OptionDTO.Response::of)
                            .toList()
                    )
                    .build();
        }
    }

}
