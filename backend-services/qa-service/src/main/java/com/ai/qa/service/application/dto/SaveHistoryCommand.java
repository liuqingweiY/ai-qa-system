package com.ai.qa.service.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveHistoryCommand {
    String userId;
    String question;
    String answer;
    LocalDateTime timeStamp;
    String session;
}
