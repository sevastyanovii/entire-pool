# Нано сервис для иммитации 100% нагрузки на узел 
Сервис предназначен для тестирования HA PROXY

## Поддерживаемые параметры
* use-connection-ms:2000 - время использования соединения потоком
* executor.max-pool-size:10 - максимльное количество входящих потоков
* executor.core-pool-size:1 - количество потоков при нулевой очереди задач
* executor.queue-capacity:50 - размер очереди задач
* scheduler.fixed-rate-ms:1500 - периодичность генерации задач для использования соединения с БД


## Запуск контейнера с настройками пулов по умолчанию 
```shell
docker run --rm -it -p 8888:8080 -e POSTGRES_PASSWORD="q_eco_d_epool_pwd" -e POSTGRES_URL="jdbc:postgresql://localhost:5432/db_epool" -e POSTGRES_USER="q_eco_d_epool" entire-pool
```
