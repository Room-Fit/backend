package com.roomfit.be.user.application.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CompleteSurveyEvent extends ApplicationEvent {
    Long userId;

    public CompleteSurveyEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
    public static CompleteSurveyEvent of(Object source, Long userId){
        return new CompleteSurveyEvent(source, userId);
    }
}
