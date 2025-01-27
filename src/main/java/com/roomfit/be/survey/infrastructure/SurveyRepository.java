package com.roomfit.be.survey.infrastructure;

import com.roomfit.be.survey.domain.Questionnaire;
import com.roomfit.be.survey.infrastructure.support.QuestionnaireRepository;
import com.roomfit.be.survey.infrastructure.support.ReplyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * QuestionnaireRepository, ReplyRepositoryCustom CustomRepository 를 통해서 Bulk 데이터 처리 최적화
 */
@Repository
public interface SurveyRepository extends JpaRepository<Questionnaire, Long>, QuestionnaireRepository, ReplyRepository {
    /**
     * 가장 최근 설문지 -> Order by 로 인한 성능 저하 우려
     */
    Optional<Questionnaire> findTopByOrderByCreatedAtDesc();
}
