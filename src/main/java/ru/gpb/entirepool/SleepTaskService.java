package ru.gpb.entirepool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Сервис для выполнения задач в БД
 *
 * @author Ivan Sevastyanov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SleepTaskService {

  private final EntityManager em;
  private final EntirePoolProperties properties;

  @Async("entirePoolExecutor")
  @Transactional(readOnly = true)
  public void execInConnectionAsync() {
    log.info("Begin to sleep.............");
    execDbSleep();
    log.info("End to sleep");
  }

  @Transactional(readOnly = true)
  public void execInConnectionSync() {
    log.info("Begin to sleep in SYNC MODE .............");
    execDbSleep();
    log.info("End to sleep in SYNC MODE");
  }

  private void execDbSleep() {
    float timeout = 1e-3f*properties.getExecutorUseConnectionMs();
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
