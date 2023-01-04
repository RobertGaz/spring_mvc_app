create table item (
  id integer not null auto_increment primary key,
  name varchar(50) not null,
  person_id int references Person(id) on delete set null
);

insert into item (name, person_id)
values ("AirPods", 1), ("AirPods Pro", 1), ("JBL GO 3", 2)