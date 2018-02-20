USE test;

DROP TABLE book;

CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(100) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  author varchar(100) DEFAULT NULL,
  isbn varchar(20) DEFAULT NULL,
  print_year int(11) DEFAULT NULL,
  read_already tinyint(1) DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci
COMMENT = 'Задание 1 на стажировку JavaRush';

SET NAMES 'utf8';

INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(1, 'Гиперион', 'Научно-фантастический роман. Первая книга тетралогии «Песни Гипериона». Роман объединяет в себе 6 новелл, каждая из которых является историей одного из главных героев.', 'Дэн Симмонс', '5237045200', 1995, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(6, 'Падение Гипериона', 'Научно-фантастический роман. Вторая книга тетралогии «Песни Гипериона», продолжение романа «Гиперион».', 'Дэн Симмонс', '5170074107', 1996, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(7, 'Эндимион', 'Научно-фантастический роман. Третья книга тетралогии «Песни Гипериона». Действие романа разворачивается через 274 года после событий, описанных в «Гиперионе» и «Падении Гипериона».', 'Дэн Симмонс', '5237002579', 1998, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(8, 'Восход Эндимиона', 'Научно-фантастический роман. Заключительная книга тетралогии «Песни Гипериона». Действие разворачивается через 4 года после событий, описанных в «Эндимионе».', 'Дэн Симмонс', '5237006752', 1998, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(9, 'Гарри Поттер и методы рационального мышления', 'Петуния вышла замуж не за Дурсля, а за профессора университета, и Гарри попал в совершенно иную среду.Частные учителя, дискуссии с отцом, сотни научных и фантастических книг.А главное — Гарри очень рационален, а это похлеще, чем укус радиоактивного паука.', 'Элиезер Юдковский (Less Wrong)', '-', 2015, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(10, 'Страж-птица', 'Фантастический рассказ-притча («кибернетическая антиутопия»)', 'Роберт Шекли', NULL, 1953, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(11, 'Сережка, Вовка, Зина и вычислительная машина', 'Прочитав эту книжку, юный читатель познакомится с историей создания вычислительной техники, с основами и принципами программирования. Для детей среднего школьного возраста.', 'Бачило Александр Геннадьевич, Ткаченко Игорь Анатольевич', '-', 1987, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(12, 'Путешествие в таинственную страну или программирование для мушкетеров', 'Основы программирования (блок-схемы, ветвления, массивы, циклы) в обёртке увлекательной истории о путешествии в страну Терминалию. Для детей среднего школьного возраста.', 'Бачило Александр Геннадьевич, Ткаченко Игорь Анатольевич', '-', 1987, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(13, 'Увлекательная математика', 'Занимательные задачи по математике.', 'Иоханнес Леман', '-', 1985, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(14, 'Найти идею', 'Введение в ТРИЗ — теорию решения изобретательских задач.', 'Генрих Альтшуллер', '978-5-9614-1494-3', 2007, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(15, 'Дизайн для НЕдизайнеров', 'Пособие для тех, кто не является профессиональным дизайнером, но вынужден время от времени заниматься созданием различных образцов «дизайнерского искусства».', 'Робин Вильямс', '5-93286-116-9', 2008, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(16, 'Шаблоны игрового программирования', 'Справочник по применению шаблонов проектирования при создании игр.', 'Роберт Найстром', '978-0-9905829-0-8', 2014, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(17, 'Философия Java', '4-е издание одного из лучших пособий по программированию на Java.', 'Брюс Эккель', '978-5-388-00003-3', 2009, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(18, 'Одураченные случайностью. О скрытой роли шанса в бизнесе и в жизни', 'Первая книга четырёхтомного философского эссе Талеба о роли неопределённости в жизни и экономике, под названием Incerto.', 'Нассим Талеб', '0-8129-7521-9', 2001, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(19, 'Чёрный лебедь', 'Вторая книга четырёхтомного философского эссе Талеба о роли неопределённости в жизни и экономике.', 'Нассим Талеб', '978-5-389-00573-0', 2007, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(20, 'Прокрустово ложе. О секретах устойчивости', 'Третья книга четырёхтомного философскго эссе Талеба. ', 'Нассим Талеб', '978-5-389-02626-1', 2010, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(21, 'Антихрупкость. Как извлечь выгоду из хаоса', 'Четвёртая книга четырёхтомного философского эссе Талеба. О том как уметь при столкновении с хаосом жизни не просто оставаться невредимым, но и становиться лучше прежнего, эволюционировать, развиваться.', 'Нассим Талеб', '978-5-389-09892-3', 2012, 0);
INSERT INTO test.book(id, title, description, author, isbn, print_year, read_already) VALUES
(22, 'Живая шляпа', 'Да что вы знаете о спойлерах!', 'Носов Николай', '-', 1984, 0);

