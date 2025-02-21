package com.example.springbatch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob; // Ensure this is correctly defined in your Spring Batch config

    @PostMapping("/start")
    public ResponseEntity<Map<String, String>> startBatch() {
        Map<String, String> response = new HashMap<>();
        try {
            // Load CSV file from classpath
            String filePath = new ClassPathResource("users.csv").getFile().getAbsolutePath();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("runId", UUID.randomUUID().toString())  // Unique execution ID
                    .addLong("time", System.currentTimeMillis())
                    .addString("filePath", filePath)  // Pass file path to Job
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(importUserJob, jobParameters);
            response.put("message", "Batch process started successfully!");
            response.put("jobId", String.valueOf(jobExecution.getId()));
            response.put("status", "STARTED");
            return ResponseEntity.ok(response);

        } catch (JobExecutionAlreadyRunningException e) {
            log.error("Batch job is already running: {}", e.getMessage());
            response.put("message", "Batch job is already running.");
            response.put("status", "FAILED");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        } catch (JobInstanceAlreadyCompleteException e) {
            log.error("Batch job has already completed and cannot be restarted: {}", e.getMessage());
            response.put("message", "Batch job has already completed and cannot be restarted.");
            response.put("status", "FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (JobRestartException e) {
            log.error("Batch job restart error: {}", e.getMessage());
            response.put("message", "Batch job cannot be restarted.");
            response.put("status", "FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (IOException e) {
            log.error("CSV file not found: {}", e.getMessage());
            response.put("message", "CSV file not found.");
            response.put("status", "FAILED");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        } catch (Exception e) {
            log.error("Error starting batch job: {}", e.getMessage(), e);
            response.put("message", "Error starting batch job.");
            response.put("error", e.getMessage());
            response.put("status", "FAILED");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
