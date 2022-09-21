package com.bravi.portfolio.repository;

import com.bravi.portfolio.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
