package com.roomfit.be.chat.presentation;

import com.roomfit.be.auth.application.dto.UserDetails;
import com.roomfit.be.chat.application.ChatRoomService;
import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.global.annontation.AuthCheck;
import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatService;

    /**
     * 채팅방 생성
     */
    @Operation(summary = "채팅방 만들기", description = "채팅방에 참여합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PostMapping("")
    public ResponseEntity<CommonResponse<ChatRoomDTO.Response>> createChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 생성 요청 객체", required = true) @RequestBody ChatRoomDTO.Create request) {

        ChatRoomDTO.Response response = chatService.createRoom(userDetails.getId(), request);
        CommonResponse<ChatRoomDTO.Response> commonResponse = ResponseFactory.success(response, "채팅방 생성 성공");
        return ResponseEntity
                .status(HttpStatus.CREATED)  // 201 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 채팅방 참여
     */
    @Operation(summary = "채팅방 참여", description = "채팅방에 참여합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PostMapping("/{chat_id}/join")
    public ResponseEntity<CommonResponse<ChatRoomDTO.Response>> enterChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("chat_id") Long chatId) {

        ChatRoomDTO.Response response = chatService.enterRoom(userDetails.getId(), chatId);
        CommonResponse<ChatRoomDTO.Response> commonResponse = ResponseFactory.success(response, "채팅방 참여 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 채팅방 퇴장
     */
    @Operation(summary = "채팅방 퇴장", description = "채팅방에서 퇴장합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PostMapping("/{chat_id}/leave")
    public ResponseEntity<CommonResponse<String>> leaveChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("chat_id") Long chatId) {

        // 채팅방 퇴장 로직 (실제로는 로직 추가 필요)
        CommonResponse<String> commonResponse = ResponseFactory.success("채팅방 퇴장 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 채팅방 메시지 조회
     */
    @Operation(summary = "채팅방 메시지 조회", description = "채팅방 내 메시지를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{chat_id}/messages")
    public ResponseEntity<CommonResponse<String>> readMessagesByChatId(
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("chat_id") Long chatId) {

        // 메시지 조회 로직 (실제로는 로직 추가 필요)
        CommonResponse<String> commonResponse = ResponseFactory.success("메시지 조회 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }
}
