package com.roomfit.be.user.presentation;

import com.roomfit.be.global.response.CommonResponse;
import com.roomfit.be.global.response.ResponseFactory;
import com.roomfit.be.user.application.UserService;
import com.roomfit.be.user.application.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @Operation(
            summary = "사용자 생성",
            description = "새로운 사용자를 생성합니다."
    )
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)  // 201 상태 코드
    public CommonResponse<UserDTO.Response> create(
            @RequestBody UserDTO.Create request) {
        UserDTO.Response response = userService.create(request);
        return ResponseFactory.success(response, "사용자 생성 성공");
    }

    /**
     * 모든 사용자 조회
     */
    @Operation(
            summary = "모든 사용자 조회",
            description = "시스템에 등록된 모든 사용자를 조회합니다."
    )
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)  // 200 상태 코드
    public CommonResponse<List<UserDTO.Response>> readAll() {
        List<UserDTO.Response> response = userService.readAll();
        return ResponseFactory.success(response, "모든 사용자 조회 성공");
    }

    /**
     * 사용자 ID로 조회
     */
    @Operation(
            summary = "사용자 ID로 조회",
            description = "특정 사용자의 ID로 사용자를 조회합니다."
    )
    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)  // 200 상태 코드
    public CommonResponse<UserDTO.Response> readById(
            @PathVariable("user_id") Long id) {
        UserDTO.Response response = userService.readById(id);
        return ResponseFactory.success(response, "사용자 조회 성공");
    }
}
