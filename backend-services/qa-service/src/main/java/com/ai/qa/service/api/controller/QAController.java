package com.ai.qa.service.api.controller;

import com.ai.qa.service.api.dto.QAHistoryDto;
import com.ai.qa.service.api.dto.SaveHistoryRequest;
import com.ai.qa.service.api.exception.ApiResponse;
import com.ai.qa.service.application.dto.SaveHistoryCommand;
import com.ai.qa.service.application.service.QAHistoryService;
import com.ai.qa.service.domain.service.QAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qa")
@RequiredArgsConstructor
public class QAController {

    private final QAService qaService;
    private QAHistoryService qaHistoryService;

    @GetMapping("/test")
    public String testFeign() {
        System.out.println("测试feign");
        return qaService.processQuestion(2L);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<QAHistoryDto>> saveHistory(@RequestBody SaveHistoryRequest request) {

        try {
            SaveHistoryCommand common = new SaveHistoryCommand();
            common.setUserId(request.getUserId());
            common.setQuestion(request.getQuestion());
            common.setAnswer(request.getAnswer());
            QAHistoryDto dto = qaHistoryService.saveHistory(common);
            return ResponseEntity.ok(ApiResponse.success(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }
}
