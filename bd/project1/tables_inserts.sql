Create table motorista(
	NomeM varchar(25),
	NCartaCond integer check(NCartaCond > 0) primary key,
	DataNasc char(8),
	Nbi integer check(NBi > 0)
);

insert into motorista values ('Manuel Duarte', 123, '14/01/76', 1234);
insert into motorista values ('Fernando Nobre', 124, '14/01/77', 1235);
insert into motorista values ('Anibal Silva', 125, '14/01/78', 1236);
insert into motorista values ('Francisco Lopes', 126,'14/01/79', 1237);


Create table telefone( 
    Telefone integer,
    Telemovel integer,
    Nbi integer check(Nbi > 0),
    primary key (Telefone, Nbi)
);

insert into telefone values (266262626, 939393939, 1234);
insert into telefone values (266262627, 939393940, 1235);
insert into telefone values (266262628, 939393941, 1236);
insert into telefone values (266262629, null, 1237);

                             
Create table modelo(
	Marca varchar(15),
	Modelo varchar(15) primary key,
	Nlugares integer,
	Consumo float
);

insert into modelo values ('Renault', 'Escape', 7, 71.00);
insert into modelo values ('Mercedes', 'CLK', 7, 91.00);
insert into modelo values ('Honda', 'Civic', 5, 51.00);
insert into modelo values ('Mercedes', 'Classe S', 5, 6.51);


Create table taxi(
    Matricula varchar(8) primary key,
    Modelo varchar(15),
    Ano integer,
    Kms integer,
    foreign key (Modelo) references modelo on delete restrict
);

insert into taxi values ('22-AA-22', 'Escape', 2015, 123098);
insert into taxi values ('21-AA-22', 'CLK', 2014, 234554);
insert into taxi values ('20-AA-22', 'Civic', 2015, 89764);
insert into taxi values ('19-AA-22', 'Classe S', 2015, 79744);


Create table servico(
	DataInicioS varchar(25),
    HoraInicioS varchar(25),
	DataFimS varchar(25),
    HoraFimS varchar(25),
    TempoServico integer, /* em minutos */
	Kms integer,
	Valor float,
	Matricula char(8),
	CoordGPSInicx float,
    CoordGPSInicy float,
    CoordGPSFimx float,
	CoordGPSFimy float,
	primary key (DataInicioS, HoraInicioS, Matricula),
    foreign key (Matricula) references taxi on delete restrict
);
	

insert into servico values ('02/01/2016', '08:13', '02/01/2016', '08:32', 19, 12, 5.25, '19-AA-22', 0, 75, 0, 76);
insert into servico values ('02/01/2016', '08:43', '02/01/2016', '08:52', 9, 7, 3.25, '20-AA-22', 38.63, -8.69, 38.64, -8.71);
insert into servico values ('02/01/2016', '09:00', '02/01/2016', '09:30', 30, 98, 53.25, '19-AA-22', 38.75, -9.18, 38.76, -9.23);
insert into servico values ('02/01/2016', '10:13', '02/01/2016', '10:29', 16, 18, 19.25, '20-AA-22', 38.56, -7.91, 38.60, -7.95);
insert into servico values ('02/01/2016', '11:10', '02/01/2016', '11:39', 29, 23, 22.25, '19-AA-22', 38.67, -8.46, 38.70, -8.50);
insert into servico values ('02/01/2016', '12:00', '02/01/2016', '13:39', 99, 21, 42.25, '20-AA-22', 38.53, -8.87, 38.80, -8.90);
insert into servico values ('02/01/2016', '15:20', '02/01/2016', '15:39', 19, 9, 12.25, '19-AA-22', 38.69, -8.93, 38.93, -9.01);
insert into servico values ('03/01/2016', '08:15', '03/01/2016', '08:35', 20, 20, 21.25, '21-AA-22', 37.45, -7.64, 37.58, -7.48);
insert into servico values ('03/01/2016', '10:00', '03/01/2016', '10:30', 30, 15, 16.25, '22-AA-22', 36.21, -8.20, 36.44, -8.00);
insert into servico values ('12/07/2016', '09:20', '12/07/2016', '09:40', 20, 8, 10.25, '19-AA-22', 35.40, -6.18, 35.20, -6.53);
insert into servico values ('12/07/2016', '09:32', '12/07/2016', '09:57', 25, 10, 13.25, '20-AA-22', 30.13, -7.02, 31.63, -7.43);
insert into servico values ('12/07/2016', '11:46', '12/07/2016', '11:54', 8, 5, 2.75, '20-AA-22', 30.67, -5.46, 31.68, -5.20);
insert into servico values ('23/09/2016', '14:15', '23/09/2016', '15:29', 74, 100, 60.25, '21-AA-22', 20.43, -9.47, 29.56, -6.90);
insert into servico values ('23/09/2016', '16:20', '23/09/2016', '16:50', 30, 45, 30.25, '21-AA-22', 32.37, -6.77, 36.82, -5.78);
insert into servico values ('30/10/2016', '08:47', '30/10/2016', '09:37', 50, 80, 49.25, '21-AA-22', 35.82, -9.24, 35.10, -9.56);
insert into servico values ('30/10/2016', '09:47', '30/10/2016', '10:37', 50, 80, 49.25, '20-AA-22', 35.82, -9.24, 35.10, -9.56);
insert into servico values ('02/11/2016', '09:13', '02/11/2016', '09:46', 33, 23, 23.50, '21-AA-22', 33.12, -8.69, 34.64, -8.61);
insert into servico values ('02/11/2016', '15:10', '02/11/2016', '16:30', 80, 110, 72.25, '20-AA-22', 30.75, -9.18, 38.76, -9.23);
insert into servico values ('10/11/2016', '10:45', '10/11/2016', '12:00', 75, 74, 40.25, '22-AA-22', 29.56, -5.91, 21.60, -6.95);
insert into servico values ('10/11/2016', '10:33', '10/11/2016', '11:33', 60, 81, 48.25, '22-AA-22', 28.67, -6.46, 22.70, -7.50);
insert into servico values ('10/11/2016', '15:02', '10/11/2016', '15:37', 35, 13, 14.25, '22-AA-22', 25.53, -7.87, 26.80, -8.30);
insert into servico values ('08/12/2016', '16:20', '08/12/2016', '16:39', 19, 6, 13.00, '22-AA-22', 29.39, -6.42, 30.65, -4.01);


Create table turno(
    DataInicioT varchar(25),
    HoraInicioT varchar(25),
    DataFimT varchar(25),
    HoraFimT varchar(25),
    KmInicio bigint,
    KmFim bigint,
    Matricula char(8),
    Nbi integer check(Nbi > 0),
    primary key (DataInicioT, Matricula),
    foreign key (Matricula) references taxi on delete restrict
);

insert into turno values ('02/01/2016', '08:00', '02/01/2016', '17:00', 79744, 79944, '19-AA-22', 1234);
insert into turno values ('02/01/2016', '08:00', '02/01/2016', '17:00', 89764, 89964, '20-AA-22', 1235);
insert into turno values ('03/01/2016', '08:00', '03/01/2016', '17:00', 234554, 234954, '21-AA-22', 1236);
insert into turno values ('03/01/2016', '08:00', '03/01/2016', '17:00', 123098, 123498, '22-AA-22', 1237);
insert into turno values ('12/07/2016', '08:00', '12/07/2016', '17:00', 65428, 65628, '19-AA-22', 1236);
insert into turno values ('23/09/2016', '08:00', '23/09/2016', '17:00', 53856, 53856, '20-AA-22', 1234);
insert into turno values ('30/10/2016', '08:00', '30/10/2016', '17:00', 105789, 105889, '20-AA-22', 1237);
insert into turno values ('02/11/2016', '08:00', '02/11/2016', '17:00', 139567, 139998, '20-AA-22', 1236);
insert into turno values ('10/11/2016', '08:00', '10/11/2016', '17:00', 173536, 173838, '22-AA-22', 1235);
insert into turno values ('08/12/2016', '08:00', '08/12/2016', '17:00', 200134, 200432, '22-AA-22', 1236);


Create table cliente1(
	NomeC varchar(25),
	Morada varchar(25),
	CodigoPostal varchar(15),
	Nif bigint check(Nif > 0) primary key
);

insert into cliente1 values ('Jose Silva', 'Rua Antonio Silva 23', '7100-434 Evora', 600700800900);
insert into cliente1 values ('Francisco Passos', 'Rua Manuel Passos 12', '7000-131 Evora', 600700800901);
insert into cliente1 values ('Pedro Sousa', 'Rua Joaquim Sousa 21', '7500-313 Evora', 600700800902);


Create table pedido(
    Nif bigint check(Nif > 0),
    MoradaInicio varchar(25),
    CodigoPostalInicio varchar(15),
    DataPedido varchar(25),
    HoraPedido varchar(25),
    Matricula char(8),
    DataInicioP varchar(25),
    HoraInicioP varchar(25),
    TempoEspera integer, /* em minutos */
    primary key (Nif, DataPedido, HoraPedido),
    foreign key (Matricula) references taxi on delete restrict,
    foreign key (Nif) references cliente1 on delete restrict
);

insert into pedido values (600700800900, 'Rua Silva Pais 33', '7120-212 Evora', '02/01/2016', '08:45', '19-AA-22', '02/01/2016', '09:00', 15);
insert into pedido values (600700800900, 'Rua Joao Freitas 21', '5240-105 Evora', '03/01/2016', '09:45', '22-AA-22', '03/01/2016', '10:00', 15);
insert into pedido values (600700800901, 'Rua Miguel Gama 12', '6348-295 Evora', '12/07/2016', '09:00', '22-AA-22', '12/07/2016', '09:10', 10);
insert into pedido values (600700800901, 'Rua Julio Batista 34', '4589-145 Evora', '23/09/2016', '10:00', '20-AA-22', '23/09/2016', '10:30', 30);
insert into pedido values (600700800902, 'Rua Jose Alfredo 54', '7063-587 Evora', '30/10/2016', '15:00', '19-AA-22', '30/10/2016', '15:30', 30);
insert into pedido values (600700800902, 'Rua Joaquim Santos 23', '9821-314 Evora', '10/11/2016', '12:00', '21-AA-22', '10/11/2016', '13:00', 60);
insert into pedido values (600700800902, 'Rua Joao Santos 44', '8374-296 Evora', '08/12/2016', '16:00', '21-AA-22', '10/11/2016', '16:20', 20);