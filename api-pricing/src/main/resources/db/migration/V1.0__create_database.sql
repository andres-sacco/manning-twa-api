create table commercial_policy (id bigint not null, active boolean not null, expire_date datetime, name varchar(50) not null, priority integer not null, start_date datetime not null, primary key (id));
create table commercial_policy_carrier (id bigint not null, carrier varchar(3) not null, percentage decimal(19,2) not null, commercial_policy_id bigint, primary key (id));

alter table commercial_policy_carrier add constraint FKntp5a7llnodxdwlb4lyq37ol2 foreign key (commercial_policy_id) references commercial_policy (id);