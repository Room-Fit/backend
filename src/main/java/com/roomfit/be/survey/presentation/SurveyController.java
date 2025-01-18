package com.roomfit.be.survey.presentation;

import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import com.roomfit.be.survey.application.SurveyService;
import com.roomfit.be.survey.application.dto.QuestionnaireDTO;
import com.roomfit.be.survey.application.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    /**
     * 설문지 생성
     */
    @Operation(summary = "새로운 설문지 생성", description = "이 API는 새로운 설문지를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "설문지 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("")
    public ResponseEntity<CommonResponse<QuestionnaireDTO.Response>> createQuestionnaire(@RequestBody QuestionnaireDTO.Create request) {
        QuestionnaireDTO.Response response = surveyService.createQuestionnaire(request);
        CommonResponse<QuestionnaireDTO.Response> commonResponse = ResponseFactory.success(response, "설문지 생성 성공");
        return ResponseEntity
                .status(HttpStatus.CREATED)  // 201 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 최신 설문지 조회
     */
    @Operation(summary = "최신 설문지 조회", description = "이 API는 최신 설문지를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "최신 설문지 조회 성공"),
            @ApiResponse(responseCode = "404", description = "설문지가 존재하지 않음")
    })
    @GetMapping("")
    public ResponseEntity<CommonResponse<QuestionnaireDTO.Response>> readQuestionnaire() {
        QuestionnaireDTO.Response response = surveyService.readLatestQuestionnaire();
        CommonResponse<QuestionnaireDTO.Response> commonResponse = ResponseFactory.success(response, "최신 설문지 조회 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 설문 답변 생성
     */
    @Operation(summary = "설문 답변 생성", description = "이 API는 설문에 대한 답변을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "설문 답변 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/reply")
    public ResponseEntity<CommonResponse<QuestionnaireDTO.Response>> createReply(@RequestBody ReplyDTO.Create request) {
        QuestionnaireDTO.Response response = surveyService.createReply(request);
        CommonResponse<QuestionnaireDTO.Response> commonResponse = ResponseFactory.success(response, "설문 답변 생성 성공");
        return ResponseEntity
                .status(HttpStatus.CREATED)  // 201 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 사용자별 설문 답변 조회
     */
    @Operation(summary = "사용자별 설문 답변 조회", description = "이 API는 특정 사용자의 설문 답변을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자별 설문 답변 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
    })
    @GetMapping("/reply")
    public ResponseEntity<CommonResponse<QuestionnaireDTO.Response>> readReply(@RequestParam("user_id") Long id) {
        QuestionnaireDTO.Response response = surveyService.readReplyByUserId(id);
        CommonResponse<QuestionnaireDTO.Response> commonResponse = ResponseFactory.success(response, "사용자별 설문 답변 조회 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }
}
