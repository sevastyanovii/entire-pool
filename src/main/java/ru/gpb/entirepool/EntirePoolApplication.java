package ru.gpb.entirepool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;

@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
public class EntirePoolApplication {

	@Value("${executor.max-pool-size:10}")
	private final int maxPoolSize;

	@Value("${executor.core-pool-size:1}")
	private final int corePoolSize;

	@Value("${executor.queue-capacity:50}")
	private final int queueCapacity;

	public static void main(String[] args) {
		SpringApplication.run(EntirePoolApplication.class, args);
	}

	@Bean
	public TaskScheduler taskScheduler() {
    return new ThreadPoolTaskScheduler(){
			{
				setPoolSize(10);
				setThreadNamePrefix("scheduler-");
			}

		};
	}

	@Bean(name = "entirePoolExecutor")
	public Executor imsExecutor() {
		return new TaskExecutorBuilder()
				.corePoolSize(corePoolSize)
				.maxPoolSize(maxPoolSize)
				.queueCapacity(queueCapacity)
				.allowCoreThreadTimeOut(true)
				.threadNamePrefix("entire-")
				.build();
	}

}
