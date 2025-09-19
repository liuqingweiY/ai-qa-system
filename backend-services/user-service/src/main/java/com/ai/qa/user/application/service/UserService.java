package com.ai.qa.user.application.service;

import com.ai.qa.user.api.exception.BusinessException;
import com.ai.qa.user.api.exception.ResponseCodeEnum;
import com.ai.qa.user.application.dto.UserLogin;
import com.ai.qa.user.application.dto.UserRegister;
import com.ai.qa.user.application.dto.UserUpdateNick;
import com.ai.qa.user.domain.repo.UserRepository;
import com.ai.qa.user.domain.service.UserDomainService;
import com.ai.qa.user.infrastructure.persisitence.entities.User;
import com.ai.qa.user.infrastructure.persisitence.repositories.UserRepositoryImpl;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository ;

    public String login(UserLogin userLogin) {
        if (userLogin.getUsername().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }
        if (userLogin.getPassword().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.PASSWORD_ERROR.getMessage());
        }
        return userDomainService.login(userLogin.getUsername(), userLogin.getPassword());
    }

    public void register(UserRegister userRegister) {
        if (userRegister.getUsername().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }
        if (userRegister.getPassword().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.PASSWORD_ERROR.getMessage());
        }
        userRepository.updateUser(userRegister.getUsername(), userRegister.getPassword());
    }

    public void updateNick(UserUpdateNick userUpdateNick) {
        if (userUpdateNick.getUsername().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }
        if (userUpdateNick.getNick().isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.NICK_ERROR.getMessage());
        }
        userRepository.updateNick(userUpdateNick.getUsername(), userUpdateNick.getNick());
    }

    public String getUser(Long userId) {

        User user = userRepository.findByUserId(userId);

        return JSON.toJSONString(user);

    }
}
