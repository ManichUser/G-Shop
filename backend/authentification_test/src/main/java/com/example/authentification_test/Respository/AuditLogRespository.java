package com.example.authentification_test.Respository;

import com.example.authentification_test.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRespository extends JpaRepository <AuditLog,Long> {
}
