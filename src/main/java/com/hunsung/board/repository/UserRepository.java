package com.hunsung.board.repository;

import com.hunsung.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //JpaRepository<ENtity, PK의 value값>
public interface UserRepository extends JpaRepository <UserEntity, String> {
    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);

    public UserEntity findByUserEmail(String userEmail);
}
