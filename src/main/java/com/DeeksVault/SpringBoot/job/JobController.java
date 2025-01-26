package com.DeeksVault.SpringBoot.job;


import com.DeeksVault.SpringBoot.company.Company;
import com.DeeksVault.SpringBoot.company.CompanyRepository;
import com.DeeksVault.SpringBoot.schedulers.JobCleanupScheduler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;
    private CompanyRepository companyRepository;
    JobCleanupScheduler jobCleanupScheduler;

    public JobController(JobService jobService , CompanyRepository companyRepository  , JobCleanupScheduler jobCleanupScheduler) {
        this.jobService = jobService;
        this.companyRepository=companyRepository;
        this.jobCleanupScheduler = jobCleanupScheduler;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs(){
        return new ResponseEntity<>(jobService.getJobs() , HttpStatus.OK);
    }

    @PostMapping("/jobs/create")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        System.out.println("in create job controller");
        jobService.createJob(job);
        return new ResponseEntity<>("Successfully added the job" , HttpStatus.CREATED);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){
        if(jobService.getJobById(id)!=null){
            return new ResponseEntity<>(jobService.getJobById(id) , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        if(jobService.deleteJob(id)){
            return  new ResponseEntity<>("Successfully deleted job" , HttpStatus.OK);
        }
        return  new ResponseEntity<>("Unable to find the requested id" , HttpStatus.NOT_FOUND);
    }

    @PutMapping("jobs/{id}")
    public  ResponseEntity<Job> updateJob(@PathVariable Long id , @RequestBody Job job){

        if(jobService.updateJob(id , job)){
            return  new ResponseEntity<>(job , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("jobs/createmany")
    public ResponseEntity<String> createManyJobs(@RequestBody List<Job> jobs){
        jobService.createJobs(jobs);
        return null;
    }

    @GetMapping("jobs/cleanup")
    public ResponseEntity<String> cleanupJobs(){
        jobCleanupScheduler.cleanUpOldJobs();
        return new ResponseEntity<>("Job cleanup triggered successfully!" , HttpStatus.OK);
    }
}
