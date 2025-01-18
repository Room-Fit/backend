package com.roomfit.be.survey.application;

import com.roomfit.be.survey.application.dto.QuestionDTO;
import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;
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

        surveyRepository.saveBulk(questionnaire);
        return readLatestQuestionnaire();
    }

    @Override
    public QuestionnaireDTO.Response readLatestQuestionnaire() {
        Questionnaire foundQuestionnaire = surveyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(()-> new QuestionnaireNotFoundException("ERROR"));

        return QuestionnaireDTO.Response.of(foundQuestionnaire);
    }
    @Transactional
    public QuestionnaireDTO.Response readLatestQuestionnaireWithReplies() {
        Questionnaire foundQuestionnaire = surveyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(()-> new QuestionnaireNotFoundException("ERROR"));

        return QuestionnaireDTO.Response.ofWithReplies(foundQuestionnaire);
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

    @Transactional
    @Override
    public QuestionnaireDTO.Response createReply(ReplyDTO.Create request) {
        surveyRepository.saveBulkReply(request);
        return readLatestQuestionnaireWithReplies();
    }

    /**
     * TODO: 로그인 들어가면, user의 id 에 따라 수정
     */
    @Override
    public QuestionnaireDTO.Response readReplyByUserId(Long id) {
        Questionnaire foundQuestionnaire = surveyRepository.findAll().get(0);
        return QuestionnaireDTO.Response.ofWithReplies(foundQuestionnaire);
    }

    private List<Question> convertToQuestions(List<QuestionDTO.Request> questions) {
        return questions.stream()
                .map(QuestionDTO.Request::toEntity)
                .toList();
    }
}
