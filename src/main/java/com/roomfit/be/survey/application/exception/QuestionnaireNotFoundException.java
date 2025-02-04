package com.roomfit.be.survey.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class QuestionnaireNotFoundException extends ApplicationException {
    public QuestionnaireNotFoundException() {
        super(ErrorCode.QUESTIONNAIRE_NOT_FOUND);
    }
}
