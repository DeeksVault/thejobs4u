package com.DeeksVault.SpringBoot.job;

import java.util.List;

public interface JobService {

    List<Job> getJobs();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJob(Long id);

    boolean updateJob(Long id , Job job);

    void createJobs(List<Job> jobs);
}
