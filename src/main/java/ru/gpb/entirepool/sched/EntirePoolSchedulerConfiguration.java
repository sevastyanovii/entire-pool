package ru.gpb.entirepool.sched;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.gpb.entirepool.EntirePoolProperties;
import ru.gpb.entirepool.SleepTaskService;

/**
 * Конфигурация для запуска фоновых задач
 *
 * @author Ivan Sevastyanov
 */
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EntirePoolSchedulerConfiguration {

  private final SleepTaskService taskService;
  private final EntirePoolProperties entirePoolProperties;

  @Scheduled(fixedRateString = "#{@entirePoolProperties.schedulerFixedRateMs}")
  public void execute() {
    for (int i = 0; i < entirePoolProperties.getExecutorMaxPoolSize(); i++) {
      taskService.execInConnectionAsync();
    }
  }

}
