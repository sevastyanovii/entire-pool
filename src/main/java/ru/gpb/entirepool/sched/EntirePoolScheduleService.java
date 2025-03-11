package ru.gpb.entirepool.sched;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gpb.entirepool.SleepTaskService;

@Slf4j
@Service
@Component
@EnableScheduling
@RequiredArgsConstructor
public class EntirePoolScheduleService {

  @Value("${executor.max-pool-size:10}")
  private final int maxPoolSize;

  private final SleepTaskService taskService;

  @Scheduled(fixedRate = 100)
  public void execute() {
    for (int i = 0; i < maxPoolSize; i++) {
      taskService.execInConnection();
    }
  }
}
