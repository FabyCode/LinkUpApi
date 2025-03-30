package com.linkup.api.repository;

import com.linkup.api.model.Mensajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensajes, Integer> {
    List<Mensajes> findByMentorIdAndEmprendedorIdOrderByFechaEnvioAsc(Long mentorId, Long emprendedorId);

    @Query("SELECT DISTINCT m.emprendedor.id FROM Mensajes m")
    List<Long> findDistinctEmprendedorIds();
}
