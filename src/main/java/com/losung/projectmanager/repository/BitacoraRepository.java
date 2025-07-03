package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    // Métodos personalizados si los necesitas
}
