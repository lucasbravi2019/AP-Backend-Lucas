package com.bravi.portfolio.repository;

import com.bravi.portfolio.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
