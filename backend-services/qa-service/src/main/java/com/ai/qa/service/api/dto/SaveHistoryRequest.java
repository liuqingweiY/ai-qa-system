package com.ai.qa.service.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveHistoryRequest {
    String userId;
    String question;
    String answer;
    LocalDateTime timeStamp;
    String session;
}
