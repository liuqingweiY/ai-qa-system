package com.ai.qa.service.application.service;

import com.ai.qa.service.api.dto.QAHistoryDto;
import com.ai.qa.service.api.exception.BusinessException;
import com.ai.qa.service.api.exception.ResponseCodeEnum;
import com.ai.qa.service.application.dto.QAHistoryQuery;
import com.ai.qa.service.application.dto.SaveHistoryCommand;
import com.ai.qa.service.domain.model.QAHistory;
import com.ai.qa.service.domain.repositories.QAHistoryRepository;
import com.ai.qa.service.infrastructure.persisitence.mappers.QAHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QAHistoryService {

    private final QAHistoryRepository qaHistoryRepository;
    private final QAHistoryMapper mapper;

    public QAHistoryDto saveHistory(SaveHistoryCommand command) {

        if (command.getUserId() == null || command.getUserId().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }
        if (command.getQuestion() == null || command.getQuestion().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.QUESTION_ERROR.getMessage());
        }
        if (command.getAnswer() == null || command.getAnswer().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.ANSWER_ERROR.getMessage());
        }
        QAHistory qaHistory = QAHistory.createNew(command.getUserId(), command.getQuestion(), command.getAnswer(),
            command.getTimeStamp(), command.getSession());
        qaHistoryRepository.save(qaHistory);
        return mapper.toDto(qaHistory);
    }

    public List<QAHistoryDto> queryUserHistory(QAHistoryQuery query) {

        if (query.getUserId() == null || query.getUserId().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }

        List<QAHistory> historyList = qaHistoryRepository.findHistoryByUserId(query.getUserId());
        return mapper.toDtoList(historyList);
    }

}
