package com.roomfit.be.chat.presentation;

import com.roomfit.be.auth.application.dto.UserDetails;
import com.roomfit.be.chat.application.ChatRoomService;
import com.roomfit.be.chat.application.dto.ChatRoomDTO;
import com.roomfit.be.chat.application.dto.MessageDTO;
import com.roomfit.be.global.response.PaginationResponse;
import com.roomfit.be.global.annontation.AuthCheck;
import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.CommonResponseWithPagination;
import com.roomfit.be.global.response.PaginationMeta;
import com.roomfit.be.global.response.ResponseFactory;
import com.roomfit.be.participation.application.ParticipationService;
import com.roomfit.be.participation.application.dto.ParticipantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatService;
    private final ParticipationService participationService;
    /**
     * 채팅방 전체 조회
     */
    @Operation(summary = "채팅방 조회하기", description = "채팅방을 PRIVATE/GROUP에 맞춰 조회 합니다.")
    @GetMapping("")
    @ResponseStatus(HttpStatus.CREATED) // Explicit status code for success response
    public CommonResponse<List<ChatRoomDTO.Response>> readAllChatRooms(
            @RequestParam(required = true, defaultValue = "GROUP") String type
    ) {
        List<ChatRoomDTO.Response> response = chatService.readAllChatRooms(type);
        return ResponseFactory.success(response, "채팅방 조회에 성공");
    }

    @Operation(summary = "채팅방 속한 사용자 조회하기", description = "채팅방에 속한 사용자를 조회 합니다.")
    @GetMapping("/{room_id}/participants")
    @ResponseStatus(HttpStatus.CREATED) // Explicit status code for success response
    public CommonResponse<List<ParticipantDTO>> readChatRoomDetailsById(
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("room_id") Long roomId
    ) {
        List<ParticipantDTO> response = participationService.readParticipantsInChatRoom(roomId);
        return ResponseFactory.success(response, "채팅방에 속한 사용자 조회 성공");
    }

    /**
     * 채팅방 생성
     */
    @Operation(summary = "채팅방 만들기", description = "채팅방에 참여합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // Explicit status code for success response
    public CommonResponse<ChatRoomDTO.Response> createChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 생성 요청 객체", required = true) @RequestBody ChatRoomDTO.Create request) {

        ChatRoomDTO.Response response = chatService.createRoom(userDetails.getId(), request);
        return ResponseFactory.success(response, "채팅방 생성 성공");
    }

    /**
     * 채팅방 참여
     */
    @Operation(summary = "채팅방 참여", description = "채팅방에 참여합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PostMapping("/{room_id}/join")
    @ResponseStatus(HttpStatus.OK) // Explicit status code for success response
    public CommonResponse<Void> enterChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("room_id") Long roomId) {

        chatService.enterRoom(userDetails.getId(), roomId);
        return ResponseFactory.success("채팅방 참여 성공");
    }

    /**
     * 채팅방 퇴장
     */
    @Operation(summary = "채팅방 퇴장", description = "채팅방에서 퇴장합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @AuthCheck()
    @PutMapping("/{room_id}/leave")
    @ResponseStatus(HttpStatus.OK) // Explicit status code for success response
    public CommonResponse<String> leaveChatRoom(
            @Parameter(hidden = true) UserDetails userDetails,
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("room_id") Long roomId) {

        // 채팅방 퇴장 로직 (실제로는 로직 추가 필요)
        return ResponseFactory.success("채팅방 퇴장 성공");
    }

    /**
     * 채팅방 메시지 조회
     */
    @Operation(summary = "채팅방 메시지 조회", description = "채팅방 내 메시지를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{room_id}/messages")
    @ResponseStatus(HttpStatus.OK) // Explicit status code for success response
    public CommonResponseWithPagination<List<MessageDTO.Response>> readMessagesByRoomId(
            @Parameter(description = "채팅방 ID", example = "1") @PathVariable("room_id") Long roomId,
            @RequestParam(required = false) Long lastMessageId,
            @RequestParam int pageSize) {

        PaginationResponse<MessageDTO.Response> messages = chatService.readMessageByRoomId(roomId, lastMessageId, pageSize);
        PaginationMeta meta = PaginationMeta.of(messages.getTotalCount(), messages.isHasNext());
        return ResponseFactory.successWithPagination(messages.getData(),"메시지 조회 성공", meta);
    }
}
