package com.roomfit.be.survey.application;

import com.roomfit.be.survey.application.dto.QuestionDTO;
import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;
import com.roomfit.be.survey.application.dto.SearchType;
import com.roomfit.be.survey.application.exception.QuestionnaireNotFoundException;
import com.roomfit.be.survey.application.exception.QuestionnaireSaveFailureException;
import com.roomfit.be.survey.application.exception.ReplySaveFailureException;
import com.roomfit.be.survey.domain.Question;
import com.roomfit.be.survey.domain.Questionnaire;
import com.roomfit.be.survey.infrastructure.QuestionRepository;
import com.roomfit.be.survey.infrastructure.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final QuestionRepository questionRepository;
    private Object questionDTOs;

    @Transactional
    @Override
    public QuestionnaireDTO.Response createQuestionnaire(QuestionnaireDTO.Create request) {
        Questionnaire questionnaire = Questionnaire.create(
                request.getTitle(),
                request.getDescription(),
                convertToQuestions(request.getQuestions())
        );
        try {
            surveyRepository.saveBulk(questionnaire);
        }catch (Exception e){
            throw new QuestionnaireSaveFailureException();
        }
        return readLatestQuestionnaire(SearchType.INCLUDE_SUB);
    }

    @Override
    public QuestionnaireDTO.Response readLatestQuestionnaire(SearchType searchType) {
        Questionnaire foundQuestionnaire = surveyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(QuestionnaireNotFoundException::new);

        return switch (searchType) {
            case INCLUDE_SUB-> QuestionnaireDTO.Response.of(foundQuestionnaire);
            case EXCLUDE_SUB-> QuestionnaireDTO.Response.builder()
                    .id(foundQuestionnaire.getId())
                    .title(foundQuestionnaire.getTitle())
                    .description(foundQuestionnaire.getDescription())
                    .build();
        };
    }
    @Transactional
    public QuestionnaireDTO.Response readLatestQuestionnaireWithReplies() {
        Questionnaire foundQuestionnaire = surveyRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(QuestionnaireNotFoundException::new);

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
    public QuestionnaireDTO.Response createReply(Long userId, ReplyDTO.Create request) {
        try {
            surveyRepository.saveBulkReply(userId, request);
        }catch (Exception e){
            throw new ReplySaveFailureException();
        }
        return readLatestQuestionnaireWithReplies();
    }

    /**
     * TODO: 로그인 들어가면, user의 id 에 따라 수정
     */
    @Transactional
    @Override
    public QuestionnaireDTO.Response readReplyByUserId(Long id) {
        QuestionnaireDTO.Response questionnaireDTO = readLatestQuestionnaire(SearchType.EXCLUDE_SUB);
        List<Question> questions = questionRepository.findByOwnerId(id, questionnaireDTO.getId());
        if(questions.isEmpty()){
            throw new RuntimeException("설문 진행해주세요");
        }
        List<QuestionDTO.Response> questionDTOs =questions.stream()
                        .map(QuestionDTO.Response::of)
                        .toList();
        log.info(questionDTOs.toString());
        questionnaireDTO.addQuestions(questionDTOs);
        return questionnaireDTO;
    }

    private List<Question> convertToQuestions(List<QuestionDTO.Request> questions) {
        return questions.stream()
                .map(QuestionDTO.Request::toEntity)
                .toList();
    }
}
