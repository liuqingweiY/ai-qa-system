package com.ai.qa.service.infrastructure.persisitence.repositories;

import com.ai.qa.service.domain.model.QAHistory;
import com.ai.qa.service.domain.repositories.QAHistoryRepository;
import com.ai.qa.service.infrastructure.persisitence.entities.QAHistoryPO;
import com.ai.qa.service.infrastructure.persisitence.mappers.QAHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QAHistoryRepositoryImpl implements QAHistoryRepository {

    private final JpaHistoryRepository jpaHistoryRepository;
    private final QAHistoryMapper mapper;

    @Override
    public void save(QAHistory qaHistory) {
        QAHistoryPO qaHistoryPO = mapper.toPO(qaHistory);
        jpaHistoryRepository.save(qaHistoryPO);
    }

    @Override
    public Optional<QAHistory> findHistoryById(Long userId) {
        QAHistoryPO qaHistoryPO = jpaHistoryRepository.findHistoryById(userId);
        return Optional.ofNullable(mapper.toDomain(qaHistoryPO));
    }

    @Override
    public List<QAHistory> findHistoryByUserId(String userId) {
        List<QAHistoryPO> qaHistoryPOList = jpaHistoryRepository.findHistoryByUserId(userId);
        return mapper.toDomainList(qaHistoryPOList);
    }

    @Override
    public List<QAHistory> findHistoryBySession(String session) {
        List<QAHistoryPO> qaHistoryPOList = jpaHistoryRepository.findHistoryBySessionId(session);
        return mapper.toDomainList(qaHistoryPOList);
    }
}
