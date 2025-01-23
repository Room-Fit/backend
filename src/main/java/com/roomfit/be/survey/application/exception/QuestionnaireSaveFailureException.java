package com.roomfit.be.survey.application.exception;

import com.roomfit.be.global.exception.ApplicationException;
import com.roomfit.be.global.exception.ErrorCode;

public class QuestionnaireSaveFailureException extends ApplicationException {
    public QuestionnaireSaveFailureException() {
        super(ErrorCode.QUESTIONNAIRE_SAVE_FAILURE);
    }
}
