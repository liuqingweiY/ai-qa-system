package com.ai.qa.service.infrastructure.persisitence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "qa_history_liuqw")
public class QAHistoryPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id")
    private String userId;
    private String question;
    private String answer;
    private LocalDateTime timestamp;
    @Column(name="session_id")
    private String sessionId;

    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
