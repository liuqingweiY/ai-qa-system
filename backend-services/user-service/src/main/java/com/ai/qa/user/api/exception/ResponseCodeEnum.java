package com.ai.qa.user.api.exception;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    USERNAME_ERROR(1000, "用户名必须输入。"),
    USERNAME_NOT_EXIT_ERROR(1001, "这个用户不存在。"),
    PASSWORD_ERROR(1002, "密码必须输入。"),
    PASSWORD_FALSE_ERROR(1002, "输入密码不正确。"),
    NICK_ERROR(1002, "昵称必须输入。"),
    UNKNOWN_ERROR(2000, "未知错误"),
    TOKEN_INVALID(2002, "无效的Token"),
    TOKEN_SIGNATURE_INVALID(2003, "无效的签名"),
    TOKEN_EXPIRED(2004, "token已过期"),
    TOKEN_MISSION(2005, "token缺失");

    private final int code;

    private final String message;
    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
   }

}
