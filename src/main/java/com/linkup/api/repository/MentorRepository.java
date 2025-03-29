package com.linkup.api.repository;

import com.linkup.api.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor findByPrincipalId(String principalId);
    Boolean existsByPrincipalId(String principalId);
}
