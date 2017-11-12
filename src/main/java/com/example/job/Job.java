package com.example.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*@Component*/
public class Job {
	private int count = 0;

	@Scheduled(cron = "*/6 * * * * ?")
	private void process() {
		System.out.println("this is scheduler task runing  " + (count++));
	}
}
