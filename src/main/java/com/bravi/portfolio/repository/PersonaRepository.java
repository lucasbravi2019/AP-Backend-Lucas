package com.bravi.portfolio.repository;

import com.bravi.portfolio.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
