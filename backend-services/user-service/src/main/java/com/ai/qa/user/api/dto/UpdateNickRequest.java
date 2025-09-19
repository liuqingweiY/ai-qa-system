package com.ai.qa.user.api.dto;

import lombok.Data;

@Data
public class UpdateNickRequest {
    private String username;
    private String nick;
}
