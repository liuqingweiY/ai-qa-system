package com.ai.qa.user.infrastructure.persisitence.repositories;

import com.ai.qa.user.api.exception.BusinessException;
import com.ai.qa.user.api.exception.ResponseCodeEnum;
import com.ai.qa.user.domain.repo.UserRepository;
import com.ai.qa.user.infrastructure.persisitence.entities.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findByUserName(String userName) {
        // 返回查询到的用户数据
        return  jpaUserRepository.findByUsername(userName).orElseThrow(() ->
            new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_NOT_EXIT_ERROR.getMessage()));
    }

    @Override
    public User findByUserId(Long userId) {
        // 返回查询到的用户数据
        return  jpaUserRepository.findById(userId).orElseThrow(() ->
            new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_NOT_EXIT_ERROR.getMessage()));
    }

    @Override
    public void updateUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        jpaUserRepository.updateOrInsert(user);
    }

    @Override
    public void updateNick(String username, String nick) {
        User user = jpaUserRepository.findByUsername(username).orElseThrow(() ->
            new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.USERNAME_NOT_EXIT_ERROR.getMessage()));
        user.setNick(nick);
        jpaUserRepository.updateOrInsert(user);
    }
}
