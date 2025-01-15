package com.roomfit.be.user.application.dto;

import com.roomfit.be.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
    /**
     * when userDTO deserialize to User Entity, the password in User DTO should be encrypted.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        private String nickname;
        private String email;
        private String role;
        private String password;
        private String birth;
        private Integer studentId;
        private String college;
        private String gender;
        private String authToken;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private String nickname;
        private String email;
        private String role;
        private String password;
        private String birth;
        private Integer studentId;
        private String college;
        private String gender;
        public static Response of(User user) {
            return Response.builder()
                    .id(user.getId())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .password(user.getPassword())
                    .birth(user.getBirth())
                    .studentId(user.getStudentId())
                    .college(user.getCollege())
                    .gender(user.getGender().name())
                    .build();
        }
    }
}
