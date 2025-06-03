package com.example.authentification_test.Respository;

import com.example.authentification_test.entities.ProducerDetails;
import com.example.authentification_test.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerDetailsRespository extends JpaRepository<ProducerDetails, Long> {
    Optional<ProducerDetails> findByUser(User user);
}
