package com.roomfit.be.survey.application.dto;

import com.roomfit.be.survey.domain.Option;
import com.roomfit.be.survey.domain.Question;
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
        private String type;
        private String optionDelimiter;;

        private List<OptionDTO.Request> options;
        public Question toEntity(){
            /**
             * TODO: 이 부분 Factory 형태를 확장해서 구분하도록 Mapper 따로 구현 할 것에 대한 고려
             */
            return switch (type) {
                case "slider" -> Question.createSlider(title, optionDelimiter, toOptionEntities());
                case "doubleSlider" -> Question.createDoubleSlider(title, optionDelimiter, toOptionEntities());
                case "selector" -> Question.createSelector(title, optionDelimiter, toOptionEntities());
                case "checkbox" -> Question.createCheckbox(title, optionDelimiter, toOptionEntities());
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
    }

}
