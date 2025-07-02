package com.example.demo.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN', 'EDIT')")
@RestController
@RequestMapping("/jobs")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job helloWorldJob;

    @PostMapping("/hello")
    public String launchHelloJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // parametro unico
                .toJobParameters();

            JobExecution execution = jobLauncher.run(helloWorldJob, params);
            return "Job eseguito con status: " + execution.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore nell'esecuzione del Job: " + e.getMessage();
        }
    }
}
