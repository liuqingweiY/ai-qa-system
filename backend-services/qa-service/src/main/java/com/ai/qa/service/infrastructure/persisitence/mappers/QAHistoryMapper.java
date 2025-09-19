package com.ai.qa.service.infrastructure.persisitence.mappers;

import com.ai.qa.service.api.dto.QAHistoryDto;
import com.ai.qa.service.domain.model.QAHistory;
import com.ai.qa.service.infrastructure.persisitence.entities.QAHistoryPO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QAHistoryMapper {

    QAHistoryPO toPO(QAHistory history);
    QAHistory toDomain(QAHistoryPO historyPO);
    QAHistoryDto toDto(QAHistory history);
    List<QAHistoryDto> toDtoList(List<QAHistory> historyList);
    List<QAHistory> toDomainList(List<QAHistoryPO> historyPOList);
}

