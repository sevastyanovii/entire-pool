package ru.gpb.entirepool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфгурация
 *
 * @author Ivan Sevastyanov
 */
@Data
@ConfigurationProperties("settings")
public class EntirePoolProperties {

  private int executorMaxPoolSize = 10;

  private int executorCorePoolSize = 1;

  private int executorQueueCapacity = 50;

  private long executorUseConnectionMs = 2000;

  private long schedulerFixedRateMs = 1500;

}
