package com.roomfit.be.survey.infrastructure.support;

import com.roomfit.be.survey.domain.Questionnaire;

public interface QuestionnaireRepository {
    void saveBulk(Questionnaire questionnaire);
}
