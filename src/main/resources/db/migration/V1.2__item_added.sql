create table item(
    id int primary key,
    name varchar(50),
    length int,
    weight numeric(10,5),
    registration timestamp,
    fake boolean,
    state varchar(50)
);