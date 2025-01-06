package com.roomfit.be.survey.application;


import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;

public interface SurveyService {
    QuestionnaireDTO.Response createQuestionnaire(QuestionnaireDTO.Create request);

    QuestionnaireDTO.Response createQuestionnaireLegacy(QuestionnaireDTO.Create request);

    QuestionnaireDTO.Response createReply(ReplyDTO.Create request);

    QuestionnaireDTO.Response readLatestQuestionnaire();

    QuestionnaireDTO.Response readReplyByUserId(Long id);
}
