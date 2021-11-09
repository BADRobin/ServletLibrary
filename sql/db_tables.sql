create table author
(
	id_author int auto_increment
		primary key,
	first_name varchar(45) not null,
	last_name varchar(45) not null,
	middle_name varchar(45) null
);

create table genre
(
	id_genre int auto_increment
		primary key,
	name varchar(45) not null
);

create table book
(
	id_book int auto_increment
		primary key,
	name varchar(45) not null,
	year date not null,
	isbn varchar(45) not null,
	description varchar(500) not null,
	id_genre int not null,
	constraint genre
		foreign key (id_genre) references genre (id_genre)
);

create index genre_idx
	on book (id_genre);

create table book_info
(
	id_book_info int auto_increment
		primary key,
	amount int not null,
	id_book int not null,
	constraint book
		foreign key (id_book) references book (id_book)
			on update cascade on delete cascade
);

create index book_idx
	on book_info (id_book);

create table order_status
(
	id_order_status int auto_increment
		primary key,
	name varchar(45) not null
);

create table person
(
	id_person int auto_increment
		primary key,
	first_name varchar(45) not null,
	last_name varchar(45) not null,
	middle_name varchar(45) null,
	phone varchar(30) not null,
	birthday date null
);

create table role
(
	id_role int auto_increment
		primary key,
	name char(32) not null
);

create table user
(
	id_user int auto_increment
		primary key,
	register_date datetime not null,
	password varchar(45) not null,
	email varchar(45) not null,
	id_person int not null,
	id_role int not null,
	constraint person
		foreign key (id_person) references person (id_person),
	constraint role
		foreign key (id_role) references role (id_role)
);

create table orders
(
	id_order int auto_increment
		primary key,
	id_user int not null,
	status int not null,
	start_date datetime not null,
	end_date datetime null,
	constraint order_order_status_id_order_status_fk
		foreign key (status) references order_status (id_order_status),
	constraint order_user_id_user_fk
		foreign key (id_user) references user (id_user)
);

create table order_books
(
	id_order_books int auto_increment
		primary key,
	id_order int not null,
	id_book int not null,
	constraint order_books_book_id_book_fk
		foreign key (id_book) references book (id_book),
	constraint order_books_order_id_order_fk
		foreign key (id_order) references orders (id_order)
);



CREATE TABLE `authors_books` (
`id_authors_books` int NOT NULL AUTO_INCREMENT,
`id_book` int DEFAULT NULL,
`id_author` int DEFAULT NULL,
  PRIMARY KEY (`id_authors_books`),
  KEY `id_book_idx` (`id_book`),
  KEY `id_author_idx` (`id_author`),
  CONSTRAINT `id_author` FOREIGN KEY (`id_author`) REFERENCES `author` (`id_author`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE ON UPDATE CASCADE
);

create index person_idx
	on user (id_person);

create index rool_idx
	on user (id_role);



