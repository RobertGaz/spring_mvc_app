create table person (
                        id integer auto_increment primary key,
                        name varchar(20) not null,
                        age integer check (age > 0),
                        email varchar(50) unique
);


insert into person (name, age, email)
values  ('Robert', 23, 'my@gmail.com'),
        ('Alina', 24, 'alina@mail.ru'),
        ('We', 1, 'we@mail.com'),
        ('Kek', 34, 'oh@me.com'),
        ('myhomeisgood', 0, 'a@mail.ru'),
        ('Kristina', 111, 'k@mail.ru'),
        ('Person', 30, 'abc@def.gh');