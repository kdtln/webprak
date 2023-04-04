INSERT INTO clients (company_name, face, email, login, password) VALUES
    ('a', 'Иванов Иван Иванович', 'ivan_ivanov@mail.ru', 'iv_ivanov', '12qwerTy'),
    ('b','Сидоров Семен Семенович', 'sidorov@gmail.com', 's_sid', 'sSqwerty');
INSERT INTO clients (company_name, face, email, login, password) VALUES
    ('postgres', 'Федотов Константин Анатольевич', 'fedot123@postgres.ru', 'post_fedot', 'qwerty'),
    ('msu', 'Кабалевская Галина Борисовна', 'kgb@cs.msu.ru', 'kgb', 'qwertY1');

INSERT INTO services (name, describe, price) VALUES
    ('Защита потребителей', 'Любые услуги, консультации по защите потребителей', 1000),
    ('Недвижимость', 'Всё, что связано с недвижимостью', 1000),
    ('Цифровое право', 'Защита в области информационных технологий', 2000);

INSERT INTO workers (full_name, address, phone, email, education, login, password, post, is_admin) VALUES
    ('Николаев Пётр Никифорович', 'Москва', '+79150879678', 'niks@gmail.com', 'МГУ ВМК','niks', 'dsfhgjhkjl', 'Администратор', 1),
    ('Юрьев Евгений Максимович', 'Подольск', '89163572938', 'uru@yandex.ru', 'НИУ ВШЭ Юридический факультет','uru', 'dfbhbdkfk', 'Юрист', 0),
    ('Алексеев Максим Петрович', 'Москва', '+79268579709', 'aleks@mail.ru', 'МГУ Юридический факультет', 'aleks', 'lnddskbjn', 'Юрист', 0);

INSERT INTO deal (client, worker, service, start_date, end_date, description) VALUES
(1, 2, 3, '2023-01-16', '2023-03-15', 'цифра'),
(2, 2, 2, '2023-02-23', '2023-02-25', 'недвижимость'),
(3, 1, 3, '2023-01-16', '2023-01-17', 'цифра')
