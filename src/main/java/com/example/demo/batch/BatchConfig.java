package com.example.demo.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean(name = "helloWorldJob")
	Job helloWorldJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new JobBuilder("helloWorldJob", jobRepository)
	            .start(helloWorldStep(jobRepository, transactionManager))
	            .build();
	}

	@Bean(name = "helloWorldStep")
	Step helloWorldStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new StepBuilder("helloWorldStep", jobRepository)
	            .tasklet(helloWorldTasklet(), transactionManager)
	            .build();
	}


    @Bean
    Tasklet helloWorldTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Hello from Spring Batch!");
            return RepeatStatus.FINISHED;
        };
    }
}
