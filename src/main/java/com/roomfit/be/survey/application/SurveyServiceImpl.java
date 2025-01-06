package com.roomfit.be.survey.application;

import com.roomfit.be.survey.application.dto.QuestionDTO;
import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.SurveyReplyDTO;
import com.roomfit.be.survey.application.exception.QuestionnaireNotFoundException;
import com.roomfit.be.survey.domain.Question;
import com.roomfit.be.survey.domain.Questionnaire;
import com.roomfit.be.survey.infrastructure.SurveyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author knu-k
 * DATE: 2025-01-06
 * TODO: 현재 Select Tag 에 대한 성능 개선 고려는 따로 진행하지 않음.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SurveyServiceImpl implements  SurveyService{
    private final SurveyRepository surveyRepository;
    @Transactional
    @Override
    public QuestionnaireDTO.Response createQuestionnaire(QuestionnaireDTO.Create request) {
        Questionnaire questionnaire = Questionnaire.create(
                request.getTitle(),
                request.getDescription(),
                convertToQuestions(request.getQuestions())
        );

        surveyRepository.saveQuestionnaire(questionnaire);
        return readLatestQuestionnaire();
    }

    @Transactional
    @Override
    public QuestionnaireDTO.Response createQuestionnaireLegacy(QuestionnaireDTO.Create request) {
        Questionnaire questionnaire = Questionnaire.create(
                request.getTitle(),
                request.getDescription(),
                convertToQuestions(request.getQuestions())
        );

        return QuestionnaireDTO.Response.of(surveyRepository.save(questionnaire));
    }

    @Override
    public QuestionnaireDTO.Response readLatestQuestionnaire() {
        Questionnaire foundQuestionnaire = surveyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(()-> new QuestionnaireNotFoundException("ERROR"));

        return QuestionnaireDTO.Response.of(foundQuestionnaire);
    }

    @Override
    public SurveyReplyDTO.QuestionReply createReply(SurveyReplyDTO.CreateReply request) {
        return null;
    }

    @Override
    public SurveyReplyDTO.QuestionReply readReplyByUserId(Long id) {
        return null;
    }

    private List<Question> convertToQuestions(List<QuestionDTO.Request> questions) {
        return questions.stream()
                .map(QuestionDTO.Request::toEntity)
                .toList();
    }
}
