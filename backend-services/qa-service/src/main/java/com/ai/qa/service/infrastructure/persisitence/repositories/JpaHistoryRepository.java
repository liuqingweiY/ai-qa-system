package com.ai.qa.service.infrastructure.persisitence.repositories;

import com.ai.qa.service.domain.model.QAHistory;
import com.ai.qa.service.infrastructure.persisitence.entities.QAHistoryPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaHistoryRepository extends JpaRepository<QAHistoryPO, Long> {

    QAHistoryPO findHistoryById(Long id);
    List<QAHistoryPO> findHistoryByUserId(String userId);
    List<QAHistoryPO> findHistoryBySessionId(String sessionId);
}
