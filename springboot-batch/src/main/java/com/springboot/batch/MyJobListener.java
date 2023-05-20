package com.springboot.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {
	
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job started");
		
	}
	
    public void afterJob(JobExecution jobExecution) {
		System.out.println("Job ended  "+ jobExecution.getStatus().toString());
	}
	
	

}
