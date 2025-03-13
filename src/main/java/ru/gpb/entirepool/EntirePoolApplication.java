package ru.gpb.entirepool;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 * Приложение Entire Pool
 *
 * @author Ivan Sevastyanov
 */
@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
public class EntirePoolApplication {

	private final EntirePoolProperties properties;

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
				.corePoolSize(properties.getExecutorCorePoolSize())
				.maxPoolSize(properties.getExecutorMaxPoolSize())
				.queueCapacity(properties.getExecutorQueueCapacity())
				.allowCoreThreadTimeOut(true)
				.threadNamePrefix("entire-")
				.build();
	}

}
