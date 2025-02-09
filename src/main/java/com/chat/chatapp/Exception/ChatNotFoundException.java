package com.chat.chatapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class ChatNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorResponse errorResponse;

    public ChatNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND.value());
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
