package com.himu.isdb.relational_db_operations_hibernate.scheduler;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
	public void reportCurrentTimeFixedRate() {
		try {
			Thread.sleep(Duration.ofMinutes(1));

		} catch (InterruptedException ignored) {

		}

		log.info("fixedRate -> the time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
	public void reportCurrentTimeFixedDelay() {
		try {
			Thread.sleep(Duration.ofMinutes(1));

		} catch (InterruptedException ignored) {

		}

		log.info("fixedDelay -> the time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(cron = "0 */2 * ? * *")
	public void reportCurrentTimeCron() {
		try {
			Thread.sleep(Duration.ofMinutes(1));

		} catch (InterruptedException ignore) {

		}

		log.info("cron -> the time is now {}", dateFormat.format(new Date()));
	}
}
