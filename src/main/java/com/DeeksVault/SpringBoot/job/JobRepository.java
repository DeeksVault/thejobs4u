package com.DeeksVault.SpringBoot.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface JobRepository extends JpaRepository<Job , Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Job j WHERE j.createdDate < :date")
    int deleteJobsPostedBefore(@Param("date") LocalDateTime thirtyDaysAgo);
}
