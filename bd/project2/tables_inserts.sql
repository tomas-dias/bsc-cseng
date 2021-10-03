Create table cliente1(
    Login varchar(25) primary key,
    Password1 varchar(25),
    NomeC varchar(25),
    Cartao bigint check(Cartao > 0),
    DataC char(8),
    Morada varchar(100)
);

insert into cliente1 values ('joseasantos365', 'Rfgeruo7452', 'José Santos', 1564738593746473, '02/08/19', 'Rua Manuel Passos 12 7000-131 Évora');
insert into cliente1 values ('mario_silva98', 'Thse859fjsh', 'Mário Silva', 3856370128462946, '15/02/19', 'Rua Joaquim Sousa 21 7500-313 Evora');


Create table pagamento(
    Login varchar(25),
    NPagamento integer,
    DiaP integer,
    MesP varchar(10),
    AnoP integer,
    Valor float,
    primary key (Login, NPagamento),
    foreign key (Login) references cliente1 on delete restrict
);

insert into pagamento values ('joseasantos365', 13, 2, 'agosto', 2019, 13.45);
insert into pagamento values ('joseasantos365', 26, 2, 'setembro', 2019, 10.25);
insert into pagamento values ('joseasantos365', 53, 2, 'outubro', 2019, 7.85);
insert into pagamento values ('joseasantos365', 67, 2, 'novembro', 2019, 13.45);
insert into pagamento values ('joseasantos365', 88, 2, 'dezembro', 2019, 10.25);
insert into pagamento values ('mario_silva98', 29, 15, 'fevereiro', 2019, 10.25);
insert into pagamento values ('mario_silva98', 71, 15, 'março', 2019, 13.45);
insert into pagamento values ('mario_silva98', 82, 15, 'abril', 2019, 7.85);
insert into pagamento values ('mario_silva98', 94, 15, 'novembro', 2019, 7.85);


Create table filme(
    URL varchar(100) primary key,
    Titulo_O varchar(100),
    Titulo_P varchar(100),
    Titulo_I varchar(100),
    Lingua_O varchar(25),
    DataE char(8),
    AnoE integer,
    Resumo varchar(255),
    Local1 varchar(100),
    Path1 varchar(100)  
);

insert into filme values ('https://imdb.to/369yR40', 'Joker', 'Joker', 'Joker', 'Inglês', '04/10', 2019, 'In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.',
                          'Estados Unidos', 'C:\Users\user1\documents\streamingservice\movies\Joker');
insert into filme values ('https://imdb.to/2quN38E', 'Avengers: Endgame', 'Vingadores: Ultimato', 'Avengers: Endgame', 'Inglês', '25/04', 2019, 'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.',
                          'Reino Unido', 'C:\Users\user1\documents\streamingservice\movies\AvengersEndgame');
insert into filme values ('https://imdb.to/38rh7TY', 'The Lord Of The Rings: The Return of the King', 'O Senhor dos Anéis: O Regresso do Rei', 'The Lord Of The Rings: The Return of the King', 'Inglês', '17/12', 2003, 'Gandalf and Aragorn lead the World of Men against Saurons army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.',
                          'Estados Unidos', 'C:\Users\user1\documents\streamingservice\movies\TheLordOfTheRingsTheReturnOfTheKing');
insert into filme values ('https://imdb.to/2LA6rs6', 'Pulp Fiction', 'Pulp Fiction', 'Pulp Fiction', 'Inglês', '21/10', 1994, 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
                          'Canada', 'C:\Users\user1\documents\streamingservice\movies\PulpFiction');
insert into filme values ('https://imdb.to/2YsrxOp', 'Harry Potter and the Chamber of Secrets', 'Harry Potter e a Câmera dos Segredos', 'Harry Potter and the Chamber of Secrets', 'Inglês', '15/11', 2002, 'An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.',
                          'Reino Unido', 'C:\Users\user1\documents\streamingservice\movies\HarryPotterAndTheChamberOfSecrets');


Create table historial1(
    Login varchar(25),
    URL varchar(100),
    DataH varchar(8),
    primary key (Login, URL),
    foreign key (Login) references cliente1 on delete restrict,
    foreign key (URL) references filme on delete restrict
);

insert into historial1 values ('joseasantos365', 'https://imdb.to/2YsrxOp', '11/08/19');
insert into historial1 values ('joseasantos365', 'https://imdb.to/369yR40', '02/12/19');
insert into historial1 values ('joseasantos365', 'https://imdb.to/38rh7TY', '12/10/19');
insert into historial1 values ('joseasantos365', 'https://imdb.to/2LA6rs6', '08/11/19');
insert into historial1 values ('mario_silva98', 'https://imdb.to/2quN38E', '12/10/19');
insert into historial1 values ('mario_silva98', 'https://imdb.to/369yR40', '02/12/19');


Create table linguaD(
    URL varchar(100),
    Lingua_D varchar(25),
    primary key (URL, Lingua_D),
    foreign key (URL) references filme on delete restrict
);

insert into linguaD values ('https://imdb.to/369yR40', 'Espanhol');
insert into linguaD values ('https://imdb.to/369yR40', 'Italiano');
insert into linguaD values ('https://imdb.to/369yR40', 'Francês');
insert into linguaD values ('https://imdb.to/2quN38E', 'Espanhol');
insert into linguaD values ('https://imdb.to/2quN38E', 'Russo');
insert into linguaD values ('https://imdb.to/2quN38E', 'Francês');
insert into linguaD values ('https://imdb.to/2quN38E', 'Mandarim');
insert into linguaD values ('https://imdb.to/38rh7TY', 'Francês');
insert into linguaD values ('https://imdb.to/38rh7TY', 'Alemão');
insert into linguaD values ('https://imdb.to/38rh7TY', 'Italiano');
insert into linguaD values ('https://imdb.to/2LA6rs6', 'Francês');
insert into linguaD values ('https://imdb.to/2LA6rs6', 'Português');
insert into linguaD values ('https://imdb.to/2LA6rs6', 'Russo');
insert into linguaD values ('https://imdb.to/2LA6rs6', 'Mandarim');
insert into linguaD values ('https://imdb.to/2YsrxOp', 'Árabe');
insert into linguaD values ('https://imdb.to/2YsrxOp', 'Português');
insert into linguaD values ('https://imdb.to/2YsrxOp', 'Espanhol');
insert into linguaD values ('https://imdb.to/2YsrxOp', 'Alemão');


Create table genero1(
    URL varchar(100),
    Genero varchar(25),
    primary key (URL, Genero),
    foreign key (URL) references filme on delete restrict
);

insert into genero1 values ('https://imdb.to/369yR40', 'Misterio');
insert into genero1 values ('https://imdb.to/369yR40', 'Drama');
insert into genero1 values ('https://imdb.to/369yR40', 'Thriler');
insert into genero1 values ('https://imdb.to/2quN38E', 'Ação');
insert into genero1 values ('https://imdb.to/2quN38E', 'Fantasia');
insert into genero1 values ('https://imdb.to/2quN38E', 'Drama');
insert into genero1 values ('https://imdb.to/2quN38E', 'Sci-Fi');
insert into genero1 values ('https://imdb.to/38rh7TY', 'Aventura');
insert into genero1 values ('https://imdb.to/38rh7TY', 'Drama');
insert into genero1 values ('https://imdb.to/38rh7TY', 'Fantasia');
insert into genero1 values ('https://imdb.to/2LA6rs6', 'Crime');
insert into genero1 values ('https://imdb.to/2LA6rs6', 'Drama');
insert into genero1 values ('https://imdb.to/2YsrxOp', 'Aventura');
insert into genero1 values ('https://imdb.to/2YsrxOp', 'Fantasia');
insert into genero1 values ('https://imdb.to/2YsrxOp', 'Thriler');
insert into genero1 values ('https://imdb.to/2YsrxOp', 'Misterio');


Create table premio_filme(
    URL varchar(100),
    PremioF varchar(100),
    primary key (URL, PremioF),
    foreign key (URL) references filme on delete restrict
);

insert into premio_filme values ('https://imdb.to/369yR40', 'Golden Lion');
insert into premio_filme values ('https://imdb.to/369yR40', 'Top 10 Films of the Year');
insert into premio_filme values ('https://imdb.to/2quN38E', 'Movie of 2019 by Peoples Choice Awards');
insert into premio_filme values ('https://imdb.to/2quN38E', 'Best Movie by MTV');
insert into premio_filme values ('https://imdb.to/2quN38E', 'Best Special Effects by Saturn Awards');
insert into premio_filme values ('https://imdb.to/38rh7TY', 'Oscar for Best Picture');
insert into premio_filme values ('https://imdb.to/38rh7TY', 'Oscar for Best Visual Effects');
insert into premio_filme values ('https://imdb.to/38rh7TY', 'Best Picture by Chicago Film Critics');
insert into premio_filme values ('https://imdb.to/38rh7TY', 'Best Action Film by Saturn Awards');
insert into premio_filme values ('https://imdb.to/2LA6rs6', 'Best Film by LA Film Critics AA');
insert into premio_filme values ('https://imdb.to/2LA6rs6', 'Best Movie by MTV');
insert into premio_filme values ('https://imdb.to/2YsrxOp', 'Nomination for Best Production Design by BAFTA');
insert into premio_filme values ('https://imdb.to/2YsrxOp', 'Nomination for Best Fantasy Film by Saturn Awards');


Create table ator(
    NomeAt varchar(25),
    DataAt char(8),
    NacionalidadeAt varchar(25),
    primary key (NomeAt, DataAt)
);

insert into ator values ('Joaquin Phoenix', '28/10/74', 'Norte-Americana');
insert into ator values ('Robert De Niro', '17/08/43', 'Norte-Americana');
insert into ator values ('Zazie Beetz', '25/05/91', 'Alemã');
insert into ator values ('Frances Conroy', '13/11/53', 'Norte-Americana');
insert into ator values ('Robert Downey Jr.', '04/04/65', 'Norte-Americana');
insert into ator values ('Chris Evans', '13/06/81', 'Norte-Americana');
insert into ator values ('Scarlett Johansson', '13/06/81', 'Dinamarquesa');
insert into ator values ('Paul Rudd', '06/04/69', 'Norte-Americana');
insert into ator values ('Ali Astin', '27/11/96', 'Norte-Americana');
insert into ator values ('Sean Astin', '25/02/71', 'Norte-Americana');
insert into ator values ('John Bach', '05/06/46', 'Francesa');
insert into ator values ('Orlando Bloom', '13/01/77', 'Inglesa');
insert into ator values ('Samuel L. Jackson', '21/12/48', 'Norte-Americana');
insert into ator values ('John Travolta', '18/02/54', 'Norte-Americana');
insert into ator values ('Bruce Willis', '19/03/55', 'Norte-Americana');
insert into ator values ('Uma Thurman', '29/04/70', 'Norte-Americana');
insert into ator values ('Daniel Radcliffe', '23/07/89', 'Inglesa');
insert into ator values ('Rupert Grint', '24/08/88', 'Francesa');
insert into ator values ('Emma Watson', '15/04/90', 'Inglesa');
insert into ator values ('Fiona Shaw', '10/07/58', 'Irlandesa');


Create table premio_ator(
    NomeAt varchar(25),
    PremioAt varchar(100),
    AnoAt integer,
    primary key (NomeAt, PremioAt, AnoAt)
);

insert into premio_ator values ('Joaquin Phoenix', 'Nomination for Oscar Best Actor', 2019);
insert into premio_ator values ('Joaquin Phoenix', 'Nomination for Golden Bear', 2009);
insert into premio_ator values ('Robert De Niro', 'Oscar Best Actor', 2003);
insert into premio_ator values ('Robert De Niro', 'Golden Globe Best Actor', 1995);
insert into premio_ator values ('Robert Downey Jr.', 'Oscar Best Actor', 2008);
insert into premio_ator values ('Robert Downey Jr.', 'Golden Globe Best Actor', 2010);
insert into premio_ator values ('Robert Downey Jr.', 'Nomination for Best Actor by MTV', 2012);
insert into premio_ator values ('Chris Evans', 'Nomination for Best Actor by Saturn Awards', 2011);
insert into premio_ator values ('Scarlett Johansson', 'Best Actress by MTV', 2012);
insert into premio_ator values ('Scarlett Johansson', 'Nomination for Golden Bear Best Actress', 2018);
insert into premio_ator values ('Paul Rudd', 'Golden Globe for Best Actor', 1998);
insert into premio_ator values ('John Bach', 'Nomination for Best Secondary Actor by MTV', 2000);
insert into premio_ator values ('Orlando Bloom', 'Golden Globe Best Actor', 2006);
insert into premio_ator values ('Orlando Bloom', 'Nomination for Oscar Best Actor', 2007);
insert into premio_ator values ('Samuel L. Jackson', 'Golden Bear for Best Actor', 1994);
insert into premio_ator values ('Samuel L. Jackson', 'Oscar Best Actor', 1988);
insert into premio_ator values ('Samuel L. Jackson', 'Golden Globe Best Actor', 1986);
insert into premio_ator values ('Samuel L. Jackson', 'Nomination for Best Actor by MTV', 2001);
insert into premio_ator values ('John Travolta', 'Best Actor by Saturn Awards', 1982);
insert into premio_ator values ('John Travolta', 'Golden Globe for Best Actor', 1983);
insert into premio_ator values ('Bruce Willis', 'Nomination for Best Actor by Saturn Awards', 1980);
insert into premio_ator values ('Daniel Radcliffe', 'Oscar Best Actor', 2002);
insert into premio_ator values ('Daniel Radcliffe', 'Nomination for Golden Bear Best Actor', 2002);


Create table diretor(
    NomeD varchar(25),
    DataD char(8),
    NacionalidadeD varchar(25),
    primary key(NomeD, DataD)
);

insert into diretor values ('Todd Phillips', '20/12/70', 'Norte-Americana');
insert into diretor values ('Anthony Russo', '03/02/70', 'Norte-Americana');
insert into diretor values ('Peter Jackson', '31/10/61', 'Neozelandesa');
insert into diretor values ('Quentin Tarantino', '27/03/63', 'Norte-Americana');
insert into diretor values ('Chris Columbus', '10/09/58', 'Norte-Americana');


Create table premio_diretor(
    NomeD varchar(25),
    PremioD varchar(100),
    AnoD integer,
    primary key (NomeD, PremioD, AnoD)
);

insert into premio_diretor values ('Todd Phillips', 'Best Director by MTV', 2019);
insert into premio_diretor values ('Anthony Russo', 'Oscar for Best Director', 2018);
insert into premio_diretor values ('Anthony Russo', 'Golden Globe for Best Director', 2016);
insert into premio_diretor values ('Peter Jackson', 'Golden Bear for Best Director', 2005);
insert into premio_diretor values ('Quentin Tarantino', 'Oscar for Best Director', 1994);
insert into premio_diretor values ('Quentin Tarantino', 'Golden Globe for Best Director', 2011);
insert into premio_diretor values ('Quentin Tarantino', 'Golden Bear for Best Director', 2003);
insert into premio_diretor values ('Chris Columbus', 'Golden Globe for Director', 2002);


Create table ator_filme(
    NomeAt varchar(25),
    DataAt varchar(8),
    URL varchar(100),
    primary key(NomeAt, DataAt, URL),
    foreign key (NomeAt, DataAt) references ator on delete restrict,
    foreign key (URL) references filme on delete restrict
);


insert into ator_filme values ('Joaquin Phoenix', '28/10/74', 'https://imdb.to/369yR40');
insert into ator_filme values ('Robert De Niro', '17/08/43', 'https://imdb.to/369yR40');
insert into ator_filme values ('Zazie Beetz', '25/05/91', 'https://imdb.to/369yR40');
insert into ator_filme values ('Frances Conroy', '13/11/53', 'https://imdb.to/369yR40');
insert into ator_filme values ('Robert Downey Jr.', '04/04/65', 'https://imdb.to/2quN38E');
insert into ator_filme values ('Chris Evans', '13/06/81', 'https://imdb.to/2quN38E');
insert into ator_filme values ('Scarlett Johansson', '13/06/81', 'https://imdb.to/2quN38E');
insert into ator_filme values ('Paul Rudd', '06/04/69', 'https://imdb.to/2quN38E');
insert into ator_filme values ('Ali Astin', '27/11/96', 'https://imdb.to/38rh7TY');
insert into ator_filme values ('Sean Astin', '25/02/71', 'https://imdb.to/38rh7TY');
insert into ator_filme values ('John Bach', '05/06/46', 'https://imdb.to/38rh7TY');
insert into ator_filme values ('Orlando Bloom', '13/01/77', 'https://imdb.to/38rh7TY');
insert into ator_filme values ('Samuel L. Jackson', '21/12/48', 'https://imdb.to/2LA6rs6');
insert into ator_filme values ('John Travolta', '18/02/54', 'https://imdb.to/2LA6rs6');
insert into ator_filme values ('Bruce Willis', '19/03/55', 'https://imdb.to/2LA6rs6');
insert into ator_filme values ('Uma Thurman', '29/04/70', 'https://imdb.to/2LA6rs6');
insert into ator_filme values ('Daniel Radcliffe', '23/07/89', 'https://imdb.to/2YsrxOp');
insert into ator_filme values ('Rupert Grint', '24/08/88', 'https://imdb.to/2YsrxOp');
insert into ator_filme values ('Emma Watson', '15/04/90', 'https://imdb.to/2YsrxOp');
insert into ator_filme values ('Fiona Shaw', '10/07/58', 'https://imdb.to/2YsrxOp');


Create table diretor_filme(
    NomeD varchar(25),
    DataD varchar(8),
    URL varchar(100),
    primary key (NomeD, DataD, URL),
    foreign key (NomeD, DataD) references diretor on delete restrict,
    foreign key (URL) references filme on delete restrict
);  

insert into diretor_filme values ('Todd Phillips', '20/12/70', 'https://imdb.to/369yR40');
insert into diretor_filme values ('Anthony Russo', '03/02/70', 'https://imdb.to/2quN38E');
insert into diretor_filme values ('Peter Jackson', '31/10/61', 'https://imdb.to/38rh7TY');
insert into diretor_filme values ('Quentin Tarantino', '27/03/63', 'https://imdb.to/2LA6rs6');
insert into diretor_filme values ('Chris Columbus', '10/09/58', 'https://imdb.to/2YsrxOp');
