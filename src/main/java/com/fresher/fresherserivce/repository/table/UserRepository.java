package com.fresher.fresherserivce.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresher.fresherserivce.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
