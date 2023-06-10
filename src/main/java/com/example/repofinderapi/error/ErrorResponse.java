package com.example.repofinderapi.error;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String Message;

    public ErrorResponse(int status, String Message) {
        this.status = status;
        this.Message = Message;
    }
}
