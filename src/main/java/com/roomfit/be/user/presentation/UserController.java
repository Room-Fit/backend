package com.roomfit.be.user.presentation;

import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import com.roomfit.be.user.application.UserService;
import com.roomfit.be.user.application.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 사용자 생성
     */
    @PostMapping("")
    public ResponseEntity<CommonResponse<UserDTO.Response>> create(
            @RequestBody UserDTO.Create request) {
        UserDTO.Response response = userService.create(request);
        CommonResponse<UserDTO.Response> commonResponse = ResponseFactory.success(response, "사용자 생성 성공");
        return ResponseEntity
                .status(HttpStatus.CREATED)  // 201 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 모든 사용자 조회
     */
    @GetMapping("")
    public ResponseEntity<CommonResponse<List<UserDTO.Response>>> readAll() {
        List<UserDTO.Response> response = userService.readAll();
        CommonResponse<List<UserDTO.Response>> commonResponse = ResponseFactory.success(response, "모든 사용자 조회 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }

    /**
     * 사용자 ID로 조회
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<CommonResponse<UserDTO.Response>> readById(
            @PathVariable("user_id") Long id) {
        UserDTO.Response response = userService.readById(id);
        CommonResponse<UserDTO.Response> commonResponse = ResponseFactory.success(response, "사용자 조회 성공");
        return ResponseEntity
                .status(HttpStatus.OK)  // 200 상태 코드
                .body(commonResponse);  // 응답 본문
    }
}
