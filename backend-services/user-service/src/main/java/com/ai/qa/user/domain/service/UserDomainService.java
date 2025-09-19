package com.ai.qa.user.domain.service;

import com.ai.qa.user.api.exception.BusinessException;
import com.ai.qa.user.api.exception.ResponseCodeEnum;
import com.ai.qa.user.domain.repo.UserRepository;
import com.ai.qa.user.infrastructure.persisitence.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtValidTime;

    private final UserRepository userDomainRepository;
    private final JwtDomainService jwtService;

    public String login(String username, String password) {

        if (username.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_ERROR.getMessage());
        }
        if (password.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.PASSWORD_ERROR.getMessage());
        }
        // 返回查询到的用户数据
        User user =  userDomainRepository.findByUserName(username);
        // 密码验证
        String passwordInput = DigestUtils.md5Hex(password);
        if (!passwordInput.equals(user.getPassword())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.PASSWORD_FALSE_ERROR.getMessage());
        }
        return jwtService.generateToken(username, jwtSecret, jwtValidTime);
    }
}
