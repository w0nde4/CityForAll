# AccessMap — бэкенд + инструменты для отладки

## Структура репозитория

```
accessmap/                  # Java-бэкенд (Spring Boot)
  Dockerfile
  pom.xml
  src/main/java/.../
    common/                 # общая инфраструктура (геометрия, базовые сущности, ошибки, CORS)
    dictionary/             # 5 справочников (curb_height, road_surface, entrance_type,
                             # crossing_type, tactile_tile_condition) — одним généric-механизмом
    ramp/                   # фича "съезд" — entity/dto/mapper/repository/service/controller
    road/                   # фича "дорога" — та же структура
    crossing/                # фича "переход" — та же структура
  src/main/resources/
    application.yml
    db/migration/V1__init.sql   # Flyway: схема + сиды справочников

postman/
  AccessMap API.postman_collection.json
  AccessMap Local.postman_environment.json

docker-compose.yml          # бэк, смотрит на БД по DB_URL (для серверной БД)
docker-compose.local.yml    # оверлей: + локальный Postgres/PostGIS
.env.example                # шаблон переменных для серверной БД
```

## Что нужно установить

- **JDK 21** — если планируешь запускать бэк без Docker, из IDE (`java -version`).
- **Docker Desktop** — для сборки и запуска через docker-compose (включает Docker Compose).
- **Postman** (десктоп-версия, не веб) — ручной клиент для проверки эндпоинтов.
- **DBeaver** — просмотр данных в БД (подключение к серверному Postgres).
- IDE с поддержкой Java/Maven (IntelliJ IDEA Community — самый безболезненный вариант).

## Запуск

### Вариант А — бэк локально из IDE, база на сервере

1. В `accessmap/src/main/resources/application.yml` ничего не трогать — значения по умолчанию перекрываются переменными окружения.
2. Задай переменные окружения `DB_URL`, `DB_USER`, `DB_PASSWORD` (в IDE — Run Configuration → Environment variables) под сервер.
3. Запусти `AccessMapApplication` или `./mvnw spring-boot:run`.
4. При старте Flyway сам накатит схему и справочники — руками ничего создавать не нужно.

### Вариант Б — всё в Docker, база на сервере

1. Скопируй `.env.example` → `.env`, заполни данными сервера.
2. `docker compose up --build`

### Вариант В — полностью локально (без сервера), для экспериментов

```
docker compose -f docker-compose.yml -f docker-compose.local.yml up --build
```
Поднимет и бэк, и локальный Postgres+PostGIS с чистой схемой.

## Проверка (Postman → DBeaver)

1. Импортируй `postman/AccessMap API.postman_collection.json` и `AccessMap Local.postman_environment.json` в Postman.
2. Выбери окружение "AccessMap Local", при необходимости поправь `baseUrl`.
3. Отправь `Ramp → Create ramp` — ожидай `201` и `id` в ответе (id сам сохранится в переменную `rampId` для следующих запросов).
4. В DBeaver: `SELECT id, ST_AsText(location), curb_height, road_access FROM ramp;` — убедись, что запись появилась.

## Как добавить новую фичу (например, "island" — островок безопасности)

Скопируй структуру `road/` (7 файлов: Entity, dto/Create*Request, dto/*Response, Mapper, Repository, Service+Impl, Controller), переименуй, впиши свои поля и таблицу в новую Flyway-миграцию `V2__island.sql`. Общий код (`common/`, `dictionary/`) трогать не нужно.

## Как добавить новый справочник

1. `@Entity @Table(name="...")` — класс-пустышка, `extends AbstractDictionaryEntity` (см. `CurbHeightVariant`).
2. Repository — одна строка, `extends DictionaryRepository<НоваяСущность>`.
3. Одна строка в `DictionaryTypeRegistry` — регистрируешь slug для URL.
4. Таблица + сиды — в Flyway-миграции.

Эндпоинт `/api/dictionaries/{slug}` появится сам — отдельный контроллер не нужен.
