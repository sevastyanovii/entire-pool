package ru.gpb.entirepool.sched;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.gpb.entirepool.SleepTaskService;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EntirePoolSchedulerConfiguration {

  @Value("${spring.datasource.hikari.maximum-pool-size:10}")
  private final int maxPoolSize;

  private final SleepTaskService taskService;

  @Scheduled(fixedRateString = "${scheduler.fixed-rate-ms:1500}")
  public void execute() {
    for (int i = 0; i < maxPoolSize; i++) {
      taskService.execInConnection();
    }
  }
}
