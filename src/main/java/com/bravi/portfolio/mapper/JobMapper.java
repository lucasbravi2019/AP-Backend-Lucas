package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.JobRequest;
import com.bravi.portfolio.dto.JobResponse;
import com.bravi.portfolio.entity.Job;
import com.bravi.portfolio.repository.JobRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JobMapper {

    private final JobRepository jobRepository;
    private final PersonaRepository personaRepository;

    public Job toEntity(JobRequest jobRequest) {
        Job job = Job.builder().build();
        if (jobRequest.getId() != null) {
            job = jobRepository.findById(jobRequest.getId())
                    .orElseThrow();
        }
        job.setCompanyName(jobRequest.getCompanyName());
        job.setJobRole(jobRequest.getJobRole());
        job.setJobTitle(jobRequest.getJobTitle());
        job.setStartDate(jobRequest.getStartDate());
        job.setEndDate(jobRequest.getEndDate());
        if (jobRequest.getPersonaId() != null) {
            job.setPersona(personaRepository.findById(jobRequest.getPersonaId())
                    .orElseThrow());
        }

        return job;
    }

    public JobResponse toDto(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .jobRole(job.getJobRole())
                .companyName(job.getCompanyName())
                .startDate(job.getStartDate())
                .endDate(job.getEndDate())
                .jobTitle(job.getJobTitle())
                .build();
    }
}
