package com.ai.qa.service.api.exception;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    USERNAME_ERROR(1000, "用户名必须输入。"),
    QUESTION_ERROR(1001, "问题必须输入。"),
    ANSWER_ERROR(1001, "回答必须输入。"),
    SESSIONID_ERROR(1001, "SessionId必须输入。");
    private final int code;

    private final String message;
    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
   }

}
