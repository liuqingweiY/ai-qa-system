package com.ai.qa.service.domain.repositories;

import com.ai.qa.service.domain.model.QAHistory;

import java.util.List;
import java.util.Optional;

public interface QAHistoryRepository {

    void save(QAHistory qaHistory);
    Optional<QAHistory> findHistoryById(Long id);
    List<QAHistory> findHistoryByUserId(String userId);
    List<QAHistory> findHistoryBySession(String session);
}
