create table person (
    id integer not null auto_increment,
    name varchar(20),
    age integer,
    email varchar(50),
    primary key (id)
);

insert into person (name, age)
values ("Robert", 23), ("Rustam", 24), ("Alina", 24)