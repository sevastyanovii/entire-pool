package ru.gpb.entirepool.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpb.entirepool.sched.EntirePoolSchedulerConfiguration;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EntirePoolController {

  private final String SCHEDULER_BEAN = "entireScheduler";
  private final ScheduledAnnotationBeanPostProcessor postProcessor;
  private final EntirePoolSchedulerConfiguration schedulerConfiguration;

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
}
