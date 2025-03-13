package ru.gpb.entirepool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация
 *
 * @author Ivan Sevastyanov
 */
@Configuration
public class SettingsConfig {

  @Bean("entirePoolProperties")
  public EntirePoolProperties entirePoolProperties() {
    return new EntirePoolProperties();
  }

}
