package com.roomfit.be.survey.presentation;

import com.roomfit.be.auth.application.dto.UserDetails;
import com.roomfit.be.global.annontation.AuthCheck;
import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import com.roomfit.be.survey.application.SurveyService;
import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;
import com.roomfit.be.survey.application.dto.SearchType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    /**
     * 설문지 생성
     */
    @Operation(
            summary = "새로운 설문지 생성",
            description = "제공된 요청 객체를 기반으로 새로운 설문지를 생성합니다."
    )
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)  // 201 상태 코드
    public CommonResponse<QuestionnaireDTO.Response> createQuestionnaire(
            @RequestBody @Parameter(description = "설문지 생성 요청 객체", required = true) QuestionnaireDTO.Create request) {
        QuestionnaireDTO.Response response = surveyService.createQuestionnaire(request);
        return ResponseFactory.success(response, "설문지 생성 성공");
    }

    /**
     * 최신 설문지 조회
     */
    @Operation(
            summary = "최신 설문지 조회",
            description = "가장 최근에 생성된 설문지를 조회합니다."
    )
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)  // 200 상태 코드
    public CommonResponse<QuestionnaireDTO.Response> readQuestionnaire() {
        QuestionnaireDTO.Response response = surveyService.readLatestQuestionnaire(SearchType.INCLUDE_SUB);
        return ResponseFactory.success(response, "최신 설문지 조회 성공");
    }

    /**
     * 설문 답변 생성
     */
    @Operation(
            summary = "설문지에 대한 답변 생성",
            description = "제공된 요청 객체를 기반으로 설문지에 대한 답변을 생성합니다."
    )
    @PostMapping("/reply")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @ResponseStatus(HttpStatus.CREATED)  // 201 상태 코드
    public CommonResponse<QuestionnaireDTO.Response> createReply(
            @Parameter(hidden = true)  UserDetails userDetails,
            @RequestBody @Parameter(description = "설문 답변 생성 요청 객체", required = true) ReplyDTO.Create request) {
        QuestionnaireDTO.Response response = surveyService.createReply(userDetails.getId(), request);
        return ResponseFactory.success(response, "설문 답변 생성 성공");
    }

    /**
     * 사용자별 설문 답변 조회
     */
    @Operation(
            summary = "특정 사용자의 설문 답변 조회",
            description = "사용자 ID를 기반으로 해당 사용자의 설문지에 대한 답변을 조회합니다."
    )
    @GetMapping("/reply")
    @ResponseStatus(HttpStatus.OK)  // 200 상태 코드
    public CommonResponse<QuestionnaireDTO.Response> readReply(
            @RequestParam("user_id") @Parameter(description = "조회하려는 사용자 ID", required = true, example = "123") Long id) {
        QuestionnaireDTO.Response response = surveyService.readReplyByUserId(id);
        return ResponseFactory.success(response, "사용자별 설문 답변 조회 성공");
    }
}
