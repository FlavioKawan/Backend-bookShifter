create table tb_belonging (position integer, game_id bigint not null, game_list_id bigint not null, primary key (game_id, game_list_id)) engine=InnoDB;
create table tb_game (game_year integer, score float(53), id bigint not null auto_increment, genre varchar(255), image_url varchar(255), long_description TEXT, platforms varchar(255), short_description TEXT, title varchar(255), primary key (id)) engine=InnoDB;
create table tb_game_list (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table tb_password_token (expiration_time datetime(6), id bigint not null auto_increment, user_id bigint, token varchar(255), primary key (id)) engine=InnoDB;
create table tb_role (id bigint not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB;
create table tb_user (is_enabled bit not null, id bigint not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id)) engine=InnoDB;
create table tb_user_roles (role_id bigint not null, user_id bigint not null) engine=InnoDB;
create table tb_verification_token (expiration_time datetime(6), id bigint not null auto_increment, user_id bigint, token varchar(255), primary key (id)) engine=InnoDB;
alter table tb_password_token add constraint UK_51u0wmbw3jkjeros6dnbl6hyj unique (user_id);
alter table tb_user add constraint UK_nkvbequw5hw90enxidyjr101b unique (email);
alter table tb_verification_token add constraint UK_3iemcfd81q5fybbrsjamk2ybn unique (user_id);
alter table tb_belonging add constraint FK5uo1pqpdk01cmvdhb733d0lm8 foreign key (game_list_id) references tb_game_list (id);
alter table tb_belonging add constraint FK2slybclee7wdfxhfltbvqkgpg foreign key (game_id) references tb_game (id);
alter table tb_password_token add constraint FKgj5ko4iscll1ni2joy29v9elh foreign key (user_id) references tb_user (id);
alter table tb_user_roles add constraint FKft1jmfcluls775jqp5142wvl8 foreign key (role_id) references tb_role (id);
alter table tb_user_roles add constraint FK19t64ocsol5x06fy2cytp7gey foreign key (user_id) references tb_user (id);
alter table tb_verification_token add constraint FKlxa2ta7gwhv0fxm8djgr29x99 foreign key (user_id) references tb_user (id);
INSERT INTO tb_game_list (name) VALUES ('Aventura e RPG');
INSERT INTO tb_game_list (name) VALUES ('Jogos de plataforma');
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Mass Effect Trilogy','XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Role-playing (RPG), Shooter', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png', 2012, 4.8);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Red Dead Redemption 2', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Role-playing (RPG), Adventure', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/2.png', 2018, 4.7);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('The Witcher 3: Wild Hunt', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Role-playing (RPG), Adventure',  'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/3.png', 2014, 4.7);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Sekiro: Shadows Die Twice', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Role-playing (RPG), Adventure', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/4.png' ,2019, 3.8);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Ghost of Tsushima', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Role-playing (RPG), Adventure', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/5.png', 2012, 4.6);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Super Mario World', 'Super Ness, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.','Platform', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png',1990, 4.7);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Hollow Knight', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Platform', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/7.png', 2017, 4.6);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Ori and the Blind Forest', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Platform', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/8.png', 2015, 4);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Cuphead', 'XBox, Playstation, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Platform', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/9.png', 2017, 4.6);
INSERT INTO tb_game (title, platforms, short_description, long_description, genre, IMAGE_URL, game_year, score) VALUES ('Sonic CD', 'Sega CD, PC', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.', 'Platform', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/10.png',1993, 4);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (1, 1, 0);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (1, 2, 1);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (1, 3, 2);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (1, 4, 3);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (1, 5, 4);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (2, 6, 0);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (2, 7, 1);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (2, 8, 2);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (2, 9, 3);
INSERT INTO tb_belonging (game_list_id, game_id, position) VALUES (2, 10, 4);
