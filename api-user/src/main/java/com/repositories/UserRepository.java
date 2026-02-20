package com.example.demo.repository;

import com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPseudo(String pseudo);
    void deleteByPseudo(String pseudo);
}