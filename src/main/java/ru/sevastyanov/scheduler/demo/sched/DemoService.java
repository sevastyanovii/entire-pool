package ru.sevastyanov.scheduler.demo.sched;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class DemoService {

  @Scheduled(cron = "*/5 * * * * *")
  public void do1() {
    log.info("><><><><><><><><><><><><><><><><");
  }
}
