package com.ai.qa.user.infrastructure.persisitence.mappers;

import com.ai.qa.user.api.dto.AuthRequest;
import com.ai.qa.user.api.dto.RegisterRequest;
import com.ai.qa.user.api.dto.UpdateNickRequest;
import com.ai.qa.user.application.dto.UserLogin;
import com.ai.qa.user.application.dto.UserRegister;
import com.ai.qa.user.application.dto.UserUpdateNick;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserLogin toUserLogin(AuthRequest request);
    UserRegister toUserRegister(RegisterRequest request);
    UserUpdateNick toUserUpdateNick(UpdateNickRequest request);
}

