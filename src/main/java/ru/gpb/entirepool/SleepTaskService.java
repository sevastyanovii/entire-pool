package ru.gpb.entirepool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Slf4j
@Service
@RequiredArgsConstructor
public class SleepTaskService {

  @Value("${use-connection-ms:2000}")
  private final long sleepMs;

  private final EntityManager em;

  @Async("entirePoolExecutor")
  @Transactional(readOnly = true)
  public void execInConnection() {
    try {
      log.info("Begin to sleep.............");
      execDbSleep();
      log.info("End to sleep");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void execDbSleep() {
    float timeout = 1e-3f*sleepMs;
    em.createNativeQuery(
            String.format("do $$\n" +
                "declare\n" +
                "  l_str varchar(255);\n" +
                "begin\n" +
                "  select pg_sleep(%s) into l_str;\n" +
                "  \n" +
                "end;\n" +
                "$$ language 'plpgsql';", timeout))
        .executeUpdate();
    log.info("success");
  }
}
