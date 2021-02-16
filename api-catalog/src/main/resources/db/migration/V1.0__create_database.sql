create table city (id bigint not null, code varchar(4) not null, name varchar(50) not null, time_zone varchar(50) not null, country_id bigint, primary key (id));
create table continent (id bigint not null, code varchar(4) not null, name varchar(50) not null, primary key (id));
create table country (id bigint not null, code varchar(4) not null, name varchar(50) not null, continent_id bigint, primary key (id));

alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country (id);
alter table country add constraint FKpymfsgrl32dy3gtl9r7rykkjg foreign key (continent_id) references continent (id);