package ru.gpb.entirepool.rest;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpb.entirepool.SleepTaskService;
import ru.gpb.entirepool.sched.EntirePoolSchedulerConfiguration;

import java.util.Optional;

/**
 * Контролер для управления/тестирования сервиса
 *
 * @author Ivan Sevastyanov
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class EntirePoolController {

  private final String SCHEDULER_BEAN = "entireScheduler";
  private final ScheduledAnnotationBeanPostProcessor postProcessor;
  private final EntirePoolSchedulerConfiguration schedulerConfiguration;
  private final SleepTaskService service;

  @GetMapping("/start")
  public ResponseEntity<String> start() {
    postProcessor.postProcessAfterInitialization(schedulerConfiguration, SCHEDULER_BEAN);
    return ResponseEntity.of(Optional.of("STARTED"));
  }

  @GetMapping("/stop")
  public ResponseEntity<String> stop() {
    postProcessor.postProcessBeforeDestruction(schedulerConfiguration, SCHEDULER_BEAN);
    return ResponseEntity.of(Optional.of("STOPPED"));
  }

  @GetMapping("/test")
  public ResponseEntity<String> sleep() {
    return Try.run(service::execInConnectionSync)
        .onSuccess(data -> log.info("Succeeded test."))
        .onFailure(ex -> log.error("Failure test. Error: " + ex.getMessage(), ex))
        .fold(ex -> ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage())
            , data -> ResponseEntity.of(Optional.of("TEST IS SUCCEEDED")));
  }

}
