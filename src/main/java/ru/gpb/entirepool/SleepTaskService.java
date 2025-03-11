package ru.gpb.entirepool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SleepTaskService {

  @Value("${use-connection-ms:10000}")
  private final long sleepMs;

  @Async("entirePoolExecutor")
  public void execInConnection() {
    try {
      log.info("Begin to sleep.............");
      Thread.sleep(sleepMs);
      log.info("End to sleep");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
