package com.linkup.api.repository;

import com.linkup.api.model.Emprendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprendedorRepository extends JpaRepository<Emprendedor, Long> {
    Emprendedor findByPrincipalId(String principalId);
    Boolean existsByPrincipalId(String principalId);
}
