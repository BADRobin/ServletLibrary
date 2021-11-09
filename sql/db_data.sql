# use web_library;

INSERT INTO web_library.role (id_role, name) VALUES (1, 'user');
INSERT INTO web_library.role (id_role, name) VALUES (2, 'admin');

INSERT INTO web_library.author (id_author, first_name, last_name, middle_name) VALUES (1, 'Александр', 'Сергеевич', 'Пушкин');
INSERT INTO web_library.author (id_author, first_name, last_name, middle_name) VALUES (2, 'Абай', 'Кунанбаев', 'Кунанбаевич');
INSERT INTO web_library.author (id_author, first_name, last_name, middle_name) VALUES (3, 'Уильям', 'Шекспир', 'Джон');
INSERT INTO web_library.author (id_author, first_name, last_name, middle_name) VALUES (4, 'Роджер', 'Желязны', 'Джозеф');

INSERT INTO web_library.book_info (id_book_info, amount, id_book) VALUES (1, 10, 1);
INSERT INTO web_library.book_info (id_book_info, amount, id_book) VALUES (2, 11, 2);
INSERT INTO web_library.book_info (id_book_info, amount, id_book) VALUES (3, 12, 3);
INSERT INTO web_library.book_info (id_book_info, amount, id_book) VALUES (4, 13, 4);


INSERT INTO web_library.genre (id_genre, name) VALUES (1, 'Классика');
INSERT INTO web_library.genre (id_genre, name) VALUES (2, 'Детектив');
INSERT INTO web_library.genre (id_genre, name) VALUES (3, 'Боевик');
INSERT INTO web_library.genre (id_genre, name) VALUES (4, 'Детская литература');
INSERT INTO web_library.genre (id_genre, name) VALUES (5, 'Фантастика');
INSERT INTO web_library.genre (id_genre, name) VALUES (6, 'Поэзия');
INSERT INTO web_library.genre (id_genre, name) VALUES (7, 'Исторический роман');
INSERT INTO web_library.genre (id_genre, name) VALUES (8, 'Приключения');
INSERT INTO web_library.genre (id_genre, name) VALUES (9, 'Фольклор');

INSERT INTO web_library.book (id_book, name, year, isbn, description, id_genre) VALUES (1, 'Евгений Онегин', '2020-03-04', '0-2018-0119-1', 'роман в стихах', 1);
INSERT INTO web_library.book (id_book, name, year, isbn, description, id_genre) VALUES (2, 'Сборник стихов', '2018-03-04', '0-2018-0119-1', 'избранные произведения', 1);
INSERT INTO web_library.book (id_book, name, year, isbn, description, id_genre) VALUES (3, 'Ромео и Джульетта', '2016-03-04', '0-2018-0119-1', 'про враждующие семьи', 1);
INSERT INTO web_library.book (id_book, name, year, isbn, description, id_genre) VALUES (4, 'Хроники Амбера', '2010-03-04', '0-2018-0119-1', 'цикл романов', 5);

INSERT INTO web_library.authors_books (id_authors_books, id_book, id_author) VALUES (1, 1, 1);
INSERT INTO web_library.authors_books (id_authors_books, id_book, id_author) VALUES (2, 2, 2);
INSERT INTO web_library.authors_books (id_authors_books, id_book, id_author) VALUES (3, 3, 3);
INSERT INTO web_library.authors_books (id_authors_books, id_book, id_author) VALUES (4, 4, 4);

INSERT INTO web_library.person (id_person, first_name, last_name, middle_name, phone, birthday) VALUES (1, 'Иван', 'Иванов', 'Иванович', '87023876353', '1986-05-06');
INSERT INTO web_library.person (id_person, first_name, last_name, middle_name, phone, birthday) VALUES (2, 'Василий', 'Васильев', 'Васильевич', '822312312312', '1996-12-12');

INSERT INTO web_library.user (id_user, register_date, password, email, id_person, id_role) VALUES (1, '2020-04-28 00:00:00', 'e10adc3949ba59abbe56e057f20f883e', 'admin@admin.com', 1, 2);
INSERT INTO web_library.user (id_user, register_date, password, email, id_person, id_role) VALUES (2, '2020-05-03 00:00:00', 'e10adc3949ba59abbe56e057f20f883e', 'test@test.ru', 2, 1);

INSERT INTO web_library.order_status (id_order_status, name) VALUES (1, 'New');
INSERT INTO web_library.order_status (id_order_status, name) VALUES (2, 'Taken');
INSERT INTO web_library.order_status (id_order_status, name) VALUES (3, 'Completed');
