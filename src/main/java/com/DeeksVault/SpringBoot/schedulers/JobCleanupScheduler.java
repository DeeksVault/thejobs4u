package com.DeeksVault.SpringBoot.schedulers;

import com.DeeksVault.SpringBoot.job.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class JobCleanupScheduler {

    private static final Logger LOGGER = Logger.getLogger(JobCleanupScheduler.class.getName());

    private final JobRepository jobRepository;

    public JobCleanupScheduler(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpOldJobs() {
        LocalDateTime thirtyDayAgo = LocalDateTime.now().minusDays(30);
        int deletedCount = jobRepository.deleteJobsPostedBefore(thirtyDayAgo);
        LOGGER.info(deletedCount + " jobs older than 30 days were deleted.");
    }
}

