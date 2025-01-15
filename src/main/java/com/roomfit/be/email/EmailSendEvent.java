package com.roomfit.be.email;

public record EmailSendEvent(String recipient, String subject, String body) {
}

