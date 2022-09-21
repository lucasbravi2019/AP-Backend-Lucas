package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.JobRequest;
import com.bravi.portfolio.dto.JobResponse;

public interface JobService {

    JobResponse createJob(JobRequest jobRequest);
    JobResponse editJob(JobRequest jobRequest);
    void deleteJob(Long id);
}
