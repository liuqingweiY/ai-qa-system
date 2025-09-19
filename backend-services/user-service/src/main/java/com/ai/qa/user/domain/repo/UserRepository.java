package com.ai.qa.user.domain.repo;

import com.ai.qa.user.infrastructure.persisitence.entities.User;

public interface UserRepository {

    User findByUserName(String userName);
    User findByUserId(Long userId);
    void updateUser(String username, String password);
    void updateNick(String username, String nick);
}
