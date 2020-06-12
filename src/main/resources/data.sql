/* Insert Books */
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1406698143110, 'Calculo I', 'Cengage Learning', 2013);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (3916881261394, 'Calculo II', 'Cengage Learning', 2013);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (0454485435039, 'Calculo III', 'Cengage Learning', 2013);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1037894226611, 'Introducao a Teoria da Computacao', 'Cengage Learning', 2007);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (0417984663509, 'Inteligencia Artificial', 'Prentice Hall', 1994);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (9990768888965, 'A Origem das Especies', 'Martin Claret', 2014);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1368187530898, 'Biologia Moderna', 'Moderna', 1999);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (7700833189301, 'Imunobiologia', 'Artmed', 2010);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (0288585769013, 'O que e a evolucao', 'Rocco', 2005);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (6242258707061, 'Programacao para Leigos', 'Alta Books', 2006);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (7352996524423, 'Restauracao Florestal', 'Oficina de Textos', 2012);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (2646186877974, 'Pericias De Engenharia - A Apuracao Dos Fatos', 'Leud', 2019);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1782097034157, 'Algoritmos: Teoria e Pratica', 'Campus', 2012);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (7747397976927, 'Sistemas Operacionais Modernos', 'Pearson', 2015);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (7877785349264, 'Encyclopedia of Algorithms', 'Springer', 2008);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (6707779951393, 'Historia da Psicologia Moderna', 'Cengage Learning', 2019);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1509238593281, 'Freud - A Trama dos Conceitos', 'Perspectiva', 2013);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (5468771271408, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Prentice Hall PTR', 2008);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (1918285060674, 'Pedagogia do oprimido', 'Paz e Terra', 2014);
INSERT INTO books (isbn, title, publisher, publication_year)
VALUES (8297071550041, 'The Universal History of Computing: From the Abacus to the Quantum Computer',
        'John Wiley & Sons', 2002);

/* Insert authors */
INSERT INTO authors (name)
VALUES ('James Stewart');
INSERT INTO authors (name)
VALUES ('Michael Sipser');
INSERT INTO authors (name)
VALUES ('Stuart J. Russell');
INSERT INTO authors (name)
VALUES ('Peter Norvig');
INSERT INTO authors (name)
VALUES ('Charles Darwin');
INSERT INTO authors (name)
VALUES ('Gilberto Martho');
INSERT INTO authors (name)
VALUES ('Jose Mariano Amabis');
INSERT INTO authors (name)
VALUES ('Kenneth Murphy');
INSERT INTO authors (name)
VALUES ('Mark Walport');
INSERT INTO authors (name)
VALUES ('Paul Travers');
INSERT INTO authors (name)
VALUES ('Ernst Mayr');
INSERT INTO authors (name)
VALUES ('Camille Mccue');
INSERT INTO authors (name)
VALUES ('Ricardo Ribeiro Rodrigues');
INSERT INTO authors (name)
VALUES ('Sergius Gandolfi');
INSERT INTO authors (name)
VALUES ('Pedro H. S. Brancalion');
INSERT INTO authors (name)
VALUES ('Simone Feigelson Deutsch');
INSERT INTO authors (name)
VALUES ('Thomas H. Cormen');
INSERT INTO authors (name)
VALUES ('Charles Eric Leiserson');
INSERT INTO authors (name)
VALUES ('Ronald Rivest');
INSERT INTO authors (name)
VALUES ('Clifford Stein');
INSERT INTO authors (name)
VALUES ('Andrew Stuart Tanenbaum');
INSERT INTO authors (name)
VALUES ('Ming Yang Kao');
INSERT INTO authors (name)
VALUES ('Duane P. Schultz');
INSERT INTO authors (name)
VALUES ('Sydney Ellen Schultz');
INSERT INTO authors (name)
VALUES ('Renato Mezan');
INSERT INTO authors (name)
VALUES ('Robert C. Martin');
INSERT INTO authors (name)
VALUES ('Michael C. Feathers');
INSERT INTO authors (name)
VALUES ('Timothy R. Ottinger');
INSERT INTO authors (name)
VALUES ('Paulo Freire');
INSERT INTO authors (name)
VALUES ('Georges Ifrah');

/* Insert Author_Book */
INSERT INTO book_author (book_id, author_id)
VALUES (1, 1);
INSERT INTO book_author (book_id, author_id)
VALUES (2, 1);
INSERT INTO book_author (book_id, author_id)
VALUES (3, 1);
INSERT INTO book_author (book_id, author_id)
VALUES (4, 2);
INSERT INTO book_author (book_id, author_id)
VALUES (5, 3);
INSERT INTO book_author (book_id, author_id)
VALUES (5, 4);
INSERT INTO book_author (book_id, author_id)
VALUES (6, 5);
INSERT INTO book_author (book_id, author_id)
VALUES (7, 6);
INSERT INTO book_author (book_id, author_id)
VALUES (7, 7);
INSERT INTO book_author (book_id, author_id)
VALUES (8, 8);
INSERT INTO book_author (book_id, author_id)
VALUES (8, 9);
INSERT INTO book_author (book_id, author_id)
VALUES (8, 10);
INSERT INTO book_author (book_id, author_id)
VALUES (9, 11);
INSERT INTO book_author (book_id, author_id)
VALUES (10, 12);
INSERT INTO book_author (book_id, author_id)
VALUES (11, 13);
INSERT INTO book_author (book_id, author_id)
VALUES (11, 14);
INSERT INTO book_author (book_id, author_id)
VALUES (11, 15);
INSERT INTO book_author (book_id, author_id)
VALUES (12, 16);
INSERT INTO book_author (book_id, author_id)
VALUES (13, 17);
INSERT INTO book_author (book_id, author_id)
VALUES (13, 18);
INSERT INTO book_author (book_id, author_id)
VALUES (13, 19);
INSERT INTO book_author (book_id, author_id)
VALUES (13, 20);
INSERT INTO book_author (book_id, author_id)
VALUES (14, 21);
INSERT INTO book_author (book_id, author_id)
VALUES (15, 22);
INSERT INTO book_author (book_id, author_id)
VALUES (16, 23);
INSERT INTO book_author (book_id, author_id)
VALUES (16, 24);
INSERT INTO book_author (book_id, author_id)
VALUES (17, 25);
INSERT INTO book_author (book_id, author_id)
VALUES (18, 26);
INSERT INTO book_author (book_id, author_id)
VALUES (18, 27);
INSERT INTO book_author (book_id, author_id)
VALUES (18, 28);
INSERT INTO book_author (book_id, author_id)
VALUES (19, 29);
INSERT INTO book_author (book_id, author_id)
VALUES (20, 30);

/* Insert type users */
INSERT INTO student_types (student_type, max_books_on_loan, max_renewal_number, number_of_days_loan,
                           number_of_days_renewal)
VALUES ('GRADUATE_STUDENT', 5, 2, 10, 10);
INSERT INTO student_types (student_type, max_books_on_loan, max_renewal_number, number_of_days_loan,
                           number_of_days_renewal)
VALUES ('TEACHER', 6, 0, 15, 0);
INSERT INTO student_types (student_type, max_books_on_loan, max_renewal_number, number_of_days_loan,
                           number_of_days_renewal)
VALUES ('POSTGRADUATE_STUDENT', 5, 1, 11, 11);
INSERT INTO student_types (student_type, max_books_on_loan, max_renewal_number, number_of_days_loan,
                           number_of_days_renewal)
VALUES ('DISTANCE_STUDENT', 6, 0, 15, 0);

/* Insert subjects */
INSERT INTO subjects (subject)
VALUES ('COMPUTING');
INSERT INTO subjects (subject)
VALUES ('HUMANITIES');
INSERT INTO subjects (subject)
VALUES ('SCIENCE');
INSERT INTO subjects (subject)
VALUES ('MATHEMATICS');
INSERT INTO subjects (subject)
VALUES ('SOCIAL_SCIENCES');
INSERT INTO subjects (subject)
VALUES ('LANGUAGES');
INSERT INTO subjects (subject)
VALUES ('ENGINEERING');
INSERT INTO subjects (subject)
VALUES ('MISCELLANEOUS');

/* Insert users */
# INSERT INTO users (username, password, role, cpf, rg, name, address, email, phone_number, student_type) VALUES ('user1','$2a$10$hrFf/h0B1mROOwSxqFaqBO8Ff7UECk0.I797beWKh5DEzFdcmgU/m', 'LIBRARIAN', '35256255523','894025768','Kelsie Pennington','840-9855 Sit Rd.','egestas@metus.co.uk','(45) 2691-5045',3);
# INSERT INTO users (username, password, role, cpf, rg, name, address, email, phone_number, student_type) VALUES ('user2','$2a$10$zOGMuz97eAiIkwQu8ohq/e3AeCgOdBCX/fs6s4CHecZTXrTA1dvXC', 'USER', '98951842123','914494733','Rinah Mclean','8656 Nam Ave','vitae.odio.sagittis@egetdictumplacerat.com','(19) 9156-6415',2);
# INSERT INTO users (username, password, role, cpf, rg, name, address, email, phone_number, user_type) VALUES ('user3', '30732932748', '472224276','Priscilla Madden','P.O. Box 480, 5304 Sit Ave','Mauris@vitaemaurissit.com','(77) 8344-4939',4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('43328145309', '307785053', 'Fitzgerald Hayes', 'P.O. Box 602, 1780 Mauris St.',
        'mauris.blandit@montesnasceturridiculus.com', '(51) 9552-7028', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('93651105607', '980473248', 'Calista Tate', 'P.O. Box 250, 7457 Elementum Ave', 'accumsan@lacusQuisque.co.uk',
        '(75) 3263-6273', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('44408859658', '436092165', 'Arden Brown', '8362 Maecenas Road', 'Morbi@sed.org', '(92) 6601-2718', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('78962919486', '135341887', 'Dorothy Kirby', '629-7254 Vestibulum, Avenue', 'vitae.sodales.nisi@sit.edu',
        '(86) 2999-3947', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('35690304742', '281368978', 'Nehru Bryan', 'Ap #853-105 Ligula. St.', 'Mauris.non@ornare.co.uk',
        '(81) 1287-0675', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('35332521378', '551654413', 'Fletcher Simon', 'P.O. Box 738, 3969 In St.',
        'Nullam.ut.nisi@adipiscinglacus.co.uk', '(66) 1160-5032', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('78175677471', '650455423', 'Stone Harmon', 'P.O. Box 883, 5713 Vitae Rd.', 'non.bibendum@Nullaegetmetus.co.uk',
        '(64) 5183-0917', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('42639447655', '190357092', 'Kennan Carson', 'P.O. Box 474, 2217 Magna St.', 'Fusce.diam@vestibulum.org',
        '(18) 1046-1771', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('95527671565', '448969095', 'Sheila Olson', '564-8904 Dui Rd.', 'luctus@turpisnonenim.co.uk', '(81) 6456-9441',
        3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('71581393118', '234150481', 'Megan Wooten', '8412 Elit Rd.', 'Curabitur@dolor.edu', '(97) 5363-6160', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('28066238264', '764108051', 'Damon Conley', '701-6669 Mauris, St.', 'sit.amet@congueturpis.org',
        '(26) 2421-7865', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('43612937809', '671897298', 'Flynn William', '7877 Mus. Road', 'luctus@ligula.edu', '(50) 5957-5909', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('73752763108', '315808108', 'Kenyon David', 'P.O. Box 924, 3112 Nibh Avenue', 'Praesent@velconvallisin.org',
        '(69) 6479-5110', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('25518943666', '146855042', 'Vernon Mcfadden', '4195 Suspendisse St.',
        'tristique.ac.eleifend@atpretiumaliquet.net', '(46) 1153-3596', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('36864563713', '958659267', 'Hu Carrillo', 'Ap #477-6884 Luctus St.', 'semper.rutrum.Fusce@consequatnec.edu',
        '(69) 0490-1937', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('76648636773', '776217485', 'Xyla Pittman', '851-8253 Sociosqu Av.', 'est@ipsumCurabitur.net', '(60) 0426-5892',
        4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('95462388150', '383509188', 'Irma Larsen', '6875 Etiam Avenue', 'et.magna@acfeugiatnon.org', '(27) 0655-2653',
        4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('98321264693', '333401631', 'Fletcher Morrison', '863-6062 Ac Rd.', 'Nulla@ultriciesligula.edu',
        '(28) 8297-1264', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('39902665622', '481026409', 'Avram Pena', 'Ap #276-8366 Est, St.', 'facilisis@dictumaugue.ca', '(11) 6247-9524',
        1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('23350366769', '884439151', 'Christen Mcmahon', '9773 Risus. Rd.', 'Integer.vitae@atrisusNunc.edu',
        '(91) 4315-4661', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('36863309372', '554432120', 'Steel Wilkins', 'Ap #195-3236 In Street', 'ligula.Aenean.gravida@magna.ca',
        '(44) 8448-4054', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('37868033603', '489671474', 'Steel Palmer', 'P.O. Box 626, 556 Non Ave', 'amet.ultricies.sem@augue.org',
        '(20) 6726-5606', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('17901743784', '856520272', 'Leila Hendrix', 'P.O. Box 984, 9197 Fringilla St.', 'neque@lobortis.org',
        '(77) 9461-8896', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('33880825207', '594803428', 'Holly Vinson', 'P.O. Box 257, 610 Nec Rd.',
        'est.vitae.sodales@ipsumSuspendisse.net', '(68) 5599-1332', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('58941316787', '486462977', 'Blaze Suarez', '837-9021 Mauris Rd.', 'dictum.augue@Sedid.net', '(91) 5674-0588',
        2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('47776777051', '416270940', 'Ferdinand Mcdonald', '474-7806 Sit Rd.', 'Aliquam.nisl.Nulla@ultricesVivamus.ca',
        '(36) 5913-6142', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('51469555699', '432347863', 'Georgia Vinson', 'P.O. Box 729, 9159 Quam Rd.', 'Suspendisse.ac.metus@risusa.net',
        '(82) 9680-4349', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('40758409415', '311720167', 'Brian Sexton', 'Ap #125-9100 Ante Rd.', 'ultrices.Vivamus@sempertellusid.edu',
        '(69) 8681-4684', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('57902907837', '570459116', 'Autumn Cervantes', 'P.O. Box 267, 1671 Nec, Rd.', 'magnis.dis@Lorem.edu',
        '(33) 9534-3717', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('40502818066', '735387204', 'Halla Goff', '108-5669 Auctor, St.', 'lacus.vestibulum@quamCurabiturvel.com',
        '(81) 8670-3963', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('45346406768', '927708917', 'Reuben Sandoval', 'Ap #252-3339 Tincidunt Rd.', 'orci@et.com', '(46) 0493-4015',
        2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('73827939922', '401217457', 'Burton Gray', 'Ap #243-6112 Id Rd.', 'consectetuer@purusDuis.ca', '(80) 1662-3251',
        2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('41701944761', '537939937', 'Lani Russo', '236-5771 Dui. Avenue', 'dolor.sit@tortordictum.co.uk',
        '(71) 3293-1052', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('86316189785', '288407904', 'Marsden Guerra', 'P.O. Box 851, 7021 Sapien St.', 'ligula.eu@Maurisquisturpis.org',
        '(10) 6126-6235', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('22512851248', '767628742', 'Tyrone Keith', 'Ap #270-229 Facilisis, Rd.', 'sagittis@lacusUtnec.co.uk',
        '(48) 1681-1638', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('76250419947', '821221513', 'Benedict Barr', '154-2205 Duis Road', 'dolor.sit@Fuscefeugiat.net',
        '(98) 6415-0522', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('57311037155', '156604804', 'April Peck', '5037 Libero Avenue', 'enim.nisl.elementum@Donecvitae.edu',
        '(42) 7245-0067', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('60489897733', '333940811', 'Solomon Justice', '9509 Penatibus St.', 'montes@diamnuncullamcorper.ca',
        '(24) 2119-7806', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('18267387032', '328934379', 'Cyrus Oneill', 'Ap #928-4543 Cras Rd.', 'nunc.sed@purusNullamscelerisque.edu',
        '(38) 9102-2197', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('32617671508', '343886590', 'Veronica Mcpherson', '7487 In Rd.', 'sed.tortor.Integer@id.edu', '(20) 9442-6060',
        1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('59562386182', '264861519', 'Fritz Snider', 'P.O. Box 603, 6883 Odio. Ave', 'cubilia@nibh.org',
        '(14) 7495-8550', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('50505269146', '917324814', 'Kenyon Stevenson', 'P.O. Box 426, 2971 Accumsan St.',
        'libero.et.tristique@Proin.edu', '(18) 7978-3144', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('14262714036', '857360597', 'Chaim Larsen', '9342 Magna. Ave', 'lorem@turpisAliquamadipiscing.ca',
        '(72) 2955-3081', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('46160877272', '142474768', 'Darryl Molina', 'Ap #568-1708 Luctus Rd.', 'sed.sem@In.com', '(88) 5361-2226', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('36389920002', '720657891', 'Finn Zimmerman', '3095 Nunc. St.', 'aliquet@magnaDuis.com', '(58) 9381-0990', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('77278647680', '305990929', 'Bo Carver', '3771 Donec Road', 'rhoncus@Classaptenttaciti.co.uk', '(99) 9033-1132',
        3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('87603592874', '127807640', 'Claire Williams', '2822 Cum Ave', 'Proin@infaucibus.net', '(90) 0770-0109', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('72941778165', '924553709', 'Scott Bowen', 'P.O. Box 824, 9337 Vitae Rd.', 'lectus@Nam.org', '(90) 9088-9738',
        2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('61125642738', '735189666', 'Tatum Suarez', 'Ap #312-1393 Laoreet Street', 'Nullam@Namnulla.org',
        '(67) 4439-7011', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('31182917437', '861260168', 'Vielka Sparks', '825-2482 Imperdiet St.',
        'Quisque.imperdiet.erat@loremvehicula.net', '(74) 8084-4497', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('91393905978', '180065071', 'Zena Bray', '8855 Maecenas Road', 'Nullam.enim@consequatpurusMaecenas.com',
        '(83) 1048-8441', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('48176183297', '829352339', 'Myles Villarreal', '907-3498 Sodales Avenue',
        'fermentum.risus.at@enimcommodohendrerit.ca', '(33) 5932-6246', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('50951105199', '516778422', 'Keely Delacruz', '1983 Ac Av.', 'convallis@necante.org', '(45) 9198-4255', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('67342591679', '588578681', 'Hamilton Moon', 'P.O. Box 119, 6181 Odio, Street',
        'erat.volutpat@eleifendvitaeerat.com', '(98) 3769-5565', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('92089384125', '345023520', 'Dexter Osborn', 'Ap #291-8063 Id Ave', 'Mauris@enim.org', '(37) 4865-3480', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('80260135625', '482643132', 'Christopher Villarreal', 'P.O. Box 365, 9284 Risus Av.', 'ipsum@utaliquam.edu',
        '(16) 7348-4325', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('81270994839', '468241144', 'Leroy Lawrence', 'P.O. Box 231, 3233 Sed Rd.', 'egestas@tristique.ca',
        '(29) 3910-4108', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('88659723063', '829340129', 'Brenden Madden', 'P.O. Box 408, 2578 Lobortis Ave',
        'facilisis.eget.ipsum@ametnulla.edu', '(83) 7514-1989', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('78789004895', '607800476', 'Gwendolyn Shannon', '331-7855 Vulputate Rd.', 'Nulla.eu.neque@odioNaminterdum.ca',
        '(97) 7279-4594', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('30248086858', '947814275', 'Lamar Cummings', 'P.O. Box 617, 5698 Lorem, Av.', 'eu.eros@vulputatenisisem.net',
        '(10) 8763-5234', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('78573278949', '896928159', 'Grady Harrison', '2969 Risus. Av.', 'Aliquam@Etiambibendumfermentum.org',
        '(34) 0769-8621', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('44887500632', '578315629', 'Jillian Blake', 'Ap #759-7126 Hendrerit Avenue',
        'Vivamus.nibh.dolor@pretiumaliquet.com', '(65) 7665-7187', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('33410877841', '354220492', 'Zephania Bailey', 'Ap #133-2840 Nisl St.',
        'ut.lacus.Nulla@tristiquepellentesque.ca', '(63) 6186-7289', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('19970254697', '632955363', 'Nigel Riggs', '6585 Nunc Av.', 'Vestibulum.ante@variuset.ca', '(53) 9917-2754', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('75446025808', '585062413', 'Lawrence Emerson', 'P.O. Box 470, 2413 Ullamcorper, Rd.', 'magna.a@vulputate.com',
        '(44) 3442-7297', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('47456126456', '411112637', 'Tanner Brown', '791-4595 Molestie St.', 'Aenean.eget@sitamet.net',
        '(31) 9581-9190', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('21756545755', '102614168', 'Baker Burns', 'P.O. Box 747, 8426 Odio. Av.', 'nunc@lorem.ca', '(80) 7456-7162',
        1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('53095178372', '291113257', 'Kirestin Bates', 'P.O. Box 544, 9647 Nulla. Road',
        'sed.consequat.auctor@blandit.org', '(13) 8321-3235', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('25741651810', '236493734', 'Madaline Holman', 'Ap #986-251 Eu Road', 'est@venenatis.edu', '(21) 9368-2674', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('66091168395', '577482731', 'Cleo Houston', '286-9518 Non, Road', 'ridiculus@egestas.ca', '(96) 4592-9918', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('70594207335', '265037220', 'Ivor Jarvis', '813 Quis Street', 'sit@cursusaenim.edu', '(97) 9289-5962', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('58122312830', '733730121', 'Jaquelyn Ramirez', '7033 Ante Avenue', 'augue.eu@necurna.net', '(63) 6876-9026',
        4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('80151236619', '602205738', 'Willow Walker', 'P.O. Box 854, 7544 Erat. Av.', 'Integer.id.magna@magnaaneque.edu',
        '(39) 1466-8874', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('70587673021', '799657713', 'Yolanda Cole', 'P.O. Box 146, 4099 Massa. Av.',
        'tincidunt.orci.quis@massaQuisqueporttitor.com', '(34) 3620-0124', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('34901136659', '119162968', 'Rana Stafford', '6940 Dolor Street', 'magna@idsapien.org', '(77) 6222-1457', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('71082607443', '256458307', 'Sydney Calhoun', 'P.O. Box 376, 6322 Mauris Ave',
        'habitant.morbi.tristique@lacusEtiam.edu', '(92) 8855-6965', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('70093034649', '384454505', 'Garrison Craft', '990-3390 Et, Rd.', 'Integer.eu@euduiCum.com', '(25) 6552-7993',
        3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('44339658760', '628179611', 'Odette Thomas', '881-9987 Imperdiet Avenue', 'lobortis@Suspendisse.ca',
        '(52) 9745-3818', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('16041443873', '680599923', 'Madeson Griffith', '187-9602 Suscipit Avenue', 'justo@aliquamenimnec.org',
        '(79) 5093-1587', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('11817269577', '715868760', 'Wendy Fowler', '4553 Est Av.', 'non.egestas.a@purusaccumsan.ca', '(28) 0152-3597',
        3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('15213167101', '972256431', 'Christen Mccray', 'P.O. Box 217, 5633 Ligula. Street',
        'dictum.cursus.Nunc@volutpat.co.uk', '(48) 5291-5534', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('90943287225', '340544409', 'Jada Erickson', '468-605 Urna Street', 'non.cursus@diamnunc.edu', '(55) 6485-9699',
        3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('46847012102', '693276335', 'Tallulah Spears', 'P.O. Box 397, 6693 Faucibus Avenue',
        'sit.amet@Aliquamauctorvelit.edu', '(48) 6045-8823', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('57161055510', '594018322', 'Marvin Powell', '159 Nulla Ave', 'posuere@Donecfringilla.ca', '(48) 1285-6259', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('10772936162', '871389598', 'Chancellor Larson', '830-578 Turpis St.', 'velit.eget.laoreet@at.edu',
        '(71) 9321-6811', 1);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('19880261511', '332956198', 'Xavier Harper', '232 Aliquam Ave', 'orci@ametdapibus.ca', '(79) 1918-2804', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('65490386493', '717887716', 'Cecilia Lambert', '3050 Enim. St.', 'ut.lacus@diamluctus.edu', '(62) 1972-8499',
        2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('55110127309', '990179509', 'Noelle Haynes', 'Ap #730-6865 Arcu. Av.',
        'mollis.lectus.pede@pellentesqueafacilisis.com', '(83) 9417-8400', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('47581526078', '390351837', 'Dean Osborne', 'Ap #364-8405 Elit Rd.',
        'nunc.interdum@consectetueradipiscingelit.org', '(80) 0687-7637', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('23650246282', '452634232', 'Allen Fitzgerald', '923-4880 Rutrum Street', 'vitae@vitaepurusgravida.edu',
        '(47) 3535-3205', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('76690371227', '592998146', 'Dawn Williamson', 'P.O. Box 236, 8737 Morbi Rd.', 'vestibulum@risus.co.uk',
        '(83) 4745-7664', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('70119654101', '152175615', 'Henry Salas', 'P.O. Box 252, 6556 Nunc St.', 'fringilla@Nunc.edu',
        '(90) 4999-8083', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('57602607877', '528846915', 'Courtney Kent', '5334 Sit Street', 'orci@Nuncullamcorper.edu', '(89) 9309-6940',
        4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('51599193597', '290761536', 'Xander Bonner', '638-7174 Nascetur Street', 'Ut.semper.pretium@loremtristique.org',
        '(52) 7284-1447', 3);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('68756008918', '928339224', 'Destiny Lara', 'P.O. Box 138, 5645 Donec Road', 'sit.amet@rutrum.com',
        '(44) 9032-4926', 4);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('78485417671', '315442465', 'Zoe Walsh', '699-2345 Et, Street',
        'odio.sagittis.semper@Etiambibendumfermentum.net', '(13) 8163-0309', 2);
INSERT INTO library_users (cpf, rg, name, address, email, phone_number, student_type_id)
VALUES ('29804531950', '274959137', 'Sopoline Mitchell', '427-9086 Nec Av.', 'Nulla@Nulla.org', '(37) 1384-0041', 1);

/* Insert book copies */
INSERT INTO copy_books (book_id, available)
VALUES (1, true);
INSERT INTO copy_books (book_id, available)
VALUES (1, true);
INSERT INTO copy_books (book_id, available)
VALUES (2, true);
INSERT INTO copy_books (book_id, available)
VALUES (3, true);
INSERT INTO copy_books (book_id, available)
VALUES (4, true);
INSERT INTO copy_books (book_id, available)
VALUES (5, true);
INSERT INTO copy_books (book_id, available)
VALUES (6, true);
INSERT INTO copy_books (book_id, available)
VALUES (7, true);
INSERT INTO copy_books (book_id, available)
VALUES (8, true);
INSERT INTO copy_books (book_id, available)
VALUES (9, true);
INSERT INTO copy_books (book_id, available)
VALUES (10, true);
INSERT INTO copy_books (book_id, available)
VALUES (11, true);
INSERT INTO copy_books (book_id, available)
VALUES (12, true);
INSERT INTO copy_books (book_id, available)
VALUES (13, true);
INSERT INTO copy_books (book_id, available)
VALUES (14, true);
INSERT INTO copy_books (book_id, available)
VALUES (15, true);
INSERT INTO copy_books (book_id, available)
VALUES (16, true);
INSERT INTO copy_books (book_id, available)
VALUES (17, true);
INSERT INTO copy_books (book_id, available)
VALUES (18, true);
INSERT INTO copy_books (book_id, available)
VALUES (19, true);
INSERT INTO copy_books (book_id, available)
VALUES (20, true);
