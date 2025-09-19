package com.ai.qa.user.application.dto;

import lombok.Data;

@Data
public class UserLogin {
    String username;
    String password;
    String secret;
    long validTime;
}
