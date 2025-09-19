package com.ai.qa.user.infrastructure.persisitence.repositories;

import com.ai.qa.user.infrastructure.persisitence.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    @Transactional
    default User updateOrInsert(User entity) {
        return save(entity);
    };
}
