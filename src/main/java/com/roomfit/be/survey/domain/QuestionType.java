package com.roomfit.be.survey.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionType {
    SLIDER, DOUBLE_SLIDER, SELECTOR, CHECKBOX;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static QuestionType forValue(String value) {
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value, e);
        }
    }
}
