package com.roomfit.be.survey.application.exception;

public class QuestionnaireNotFoundException extends RuntimeException{
    public QuestionnaireNotFoundException(String message) {
        super(message);
    }
}
