package com.roomfit.be.survey.application;


import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;
import com.roomfit.be.survey.application.dto.SearchType;

public interface SurveyService {
    QuestionnaireDTO.Response createQuestionnaire(QuestionnaireDTO.Create request);

    QuestionnaireDTO.Response createQuestionnaireLegacy(QuestionnaireDTO.Create request);

    QuestionnaireDTO.Response createReply(Long userId, ReplyDTO.Create request);

    QuestionnaireDTO.Response readLatestQuestionnaire(SearchType searchType);

    QuestionnaireDTO.Response readReplyByUserId(Long id);
}
