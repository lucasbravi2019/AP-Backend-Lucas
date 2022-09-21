package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.JobRequest;
import com.bravi.portfolio.dto.JobResponse;
import com.bravi.portfolio.mapper.JobMapper;
import com.bravi.portfolio.repository.JobRepository;
import com.bravi.portfolio.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;

    @Override
    public JobResponse createJob(JobRequest jobRequest) {
        return jobMapper.toDto(jobRepository.save(jobMapper.toEntity(jobRequest)));
    }

    @Override
    public JobResponse editJob(JobRequest jobRequest) {
        return jobMapper.toDto(jobRepository.save(jobMapper.toEntity(jobRequest)));
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

}
