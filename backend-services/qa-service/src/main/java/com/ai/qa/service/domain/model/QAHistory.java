package com.ai.qa.service.domain.model;

import java.time.LocalDateTime;

public class QAHistory {
    private String id;
    private String userId;
    private String question;
    private String answer;
    private LocalDateTime timeStamp;
    private String sessionId;

    public String getId() {
        return this.id;
    }

    public String getAnswer(String question) {
        return answer;
    }
    public QAHistory() {

    }
    private QAHistory(String userId, String question, String answer, LocalDateTime timeStamp, String sessionId) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.timeStamp = timeStamp;
        this.sessionId = sessionId;
    }

    public static QAHistory createNew(String userId, String question, String answer, LocalDateTime timeStamp, String sessionId) {
        return new QAHistory(userId, question, answer, timeStamp, sessionId);
    }

}
