DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO meals (description,date_time, calories, user_id)
VALUES ('Завтрак','2020-09-28 10:00:00', 500, 100000),
       ('Обед','2020-09-28 13:00:00', 1000, 100000),
       ('Ужин','2020-09-28 20:00:00', 500, 100000),
       ('Еда на граничное значение','2020-09-29 00:00:00', 100, 100000),
       ('Завтрак','2020-09-29 10:00:00', 1000, 100000),
       ('Обед','2020-09-29 13:00:00', 500, 100000),
       ('Ужин','2020-09-29 20:00:00', 410, 100000),
       ('Админ ланч','2015-07-01 14:00:00', 510, 100001),
       ('Админ ужин','2015-07-01 21:00:00', 1500, 100001);
