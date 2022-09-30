package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.JobRequest;
import com.bravi.portfolio.dto.JobResponse;
import com.bravi.portfolio.entity.Job;
import com.bravi.portfolio.repository.JobRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        job.setEndDate(getEndDate(jobRequest.getEndDate(), jobRequest.getIsPresent()));
        if (jobRequest.getPersonaId() != null) {
            job.setPersona(personaRepository.findById(jobRequest.getPersonaId())
                    .orElseThrow());
        }

        return job;
    }

    private LocalDate getEndDate(LocalDate endDate, Boolean isPresent) {
        if (Boolean.TRUE.equals(isPresent)) {
            return LocalDate.of(9_999, 9, 9);
        }
        return endDate;
    }

    public JobResponse toDto(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .jobRole(job.getJobRole())
                .companyName(job.getCompanyName())
                .startDate(formatDate(job.getStartDate()))
                .endDate(formatDate(job.getEndDate()))
                .jobTitle(job.getJobTitle())
                .build();
    }

    private String formatDate(LocalDate date) {
        if (LocalDate.of(9999, 9, 9).equals(date)) {
            return "Present";
        }
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(date);
    }

    public List<JobResponse> toDtoList(List<Job> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
