package com.roomfit.be.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {
    Long id;
    String email;
    String nickname;
    String userRole;
    String surveyStage;
    public static UserDetails of(Long id, String email, String nickname, String userRole, String surveyStage){
        return UserDetails.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                .userRole(userRole)
                .surveyStage(surveyStage)
                .build();
    }
}
