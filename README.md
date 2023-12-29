# Backend-часть сайта на Java.

Backend-часть проекта предполагает реализацию следующего функционала:
-
- Авторизация и аутентификация пользователей.
- Распределение ролей между пользователями: пользователь и администратор.
- CRUD-операции для объявлений и комментариев: администратор может удалять 
  или редактировать все объявления и комментарии, а пользователи — только свои.
- Возможность для пользователей оставлять комментарии под каждым объявлением.
- Показ и сохранение картинок объявлений, а также аватарок пользователей.

Для реализации задач ТЗ-проекта использовали:
-
- Подключение БД PostgreSQL.
- Создание сущностей: пользователь, объявление, комментарий. DTO - некоторых сущностей.
- Репозитории, сервисы, контроллеры.
- Сервисы, которые маппят (устанавливают соответствие) из сущностей в DTO и обратно.
- Установили связь между контроллерами, сервисами и репозиториями.
- Приложение работает в соответствии с OpenAPI-спецификацией.
- Написаны методы для получения и обновления картинок пользователя/объявлений.

Используемые технологии:
-
- liquibase
- lombok
- Spring Framework
- Apache Commons
- JPA
- JDK
- MapStruct
- 

Работу выполняют Java-студенты:
- 
- Дарья Чулпанова
- Елена Деноева
- Антон Арсентьев