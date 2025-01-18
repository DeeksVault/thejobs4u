package com.DeeksVault.SpringBoot.job.implementation;

import com.DeeksVault.SpringBoot.job.Job;
import com.DeeksVault.SpringBoot.job.JobRepository;
import com.DeeksVault.SpringBoot.job.JobService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {

    JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

//    private List<Job> jobs = new ArrayList<>();
    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {
        if(!jobRepository.findById(id).isPresent()){
            return false;
        }
        try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job job) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job updatedJob = jobOptional.get();
            updatedJob.setTitle(job.getTitle());
            updatedJob.setDescription(job.getDescription());
            updatedJob.setMinSalary(job.getMinSalary());
            updatedJob.setMaxSalary(job.getMaxSalary());
            updatedJob.setLocation(job.getLocation());
            jobRepository.save(updatedJob);
            return true;
        }
        return false;
    }

}
