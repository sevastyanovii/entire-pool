# Нано сервис для иммитации 100% нагрузки на узел 
Сервис предназначен для тестирования HA PROXY

## Поддерживаемые параметры
* POSTGRES_URL:jdbc:postgresql://postgres:5432/db_entirepool?currentSchema="q_eco_d_epool"
* POSTGRES_USER:q_eco_d_epool
* POSTGRES_PASSWORD:q_eco_d_epool_pwd
* DB_POOL_MINIMUM_IDLE:1
* DB_POOL_MAXSIZE:10
* EXECUTOR_MAX_POOL_SIZE:10 - максимльное количество входящих потоков
* EXECUTOR_CORE_POOL_SIZE:1 - количество потоков при нулевой очереди задач
* EXECUTOR_QUEUE_CAPACITY:50 - размер очереди задач
* EXECUTOR_USE_CONNECTION_MS:2000 - время использования соединения потоком
* SCHEDULER_FIXED_RATE_MS:1500 - периодичность генерации задач для использования соединения с БД

## Запуск контейнера с настройками пулов по умолчанию 
```shell
docker run --rm -it -p 8888:8080 -e POSTGRES_PASSWORD="q_eco_d_epool_pwd" -e POSTGRES_URL="jdbc:postgresql://localhost:5432/db_epool" -e POSTGRES_USER="q_eco_d_epool" entire-pool
```
