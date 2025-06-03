package com.example.authentification_test.Respository;

import com.example.authentification_test.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository <User,Long>{
    Optional<User> findByEmail(String email);

}
