package com.nadhem.users.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nadhem.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User>findByEmail(String email);
    @Query("select u from User u where u.user_id = ?1")
    User findUserById(Long id);
}
