create table item
(
    id           int primary key,
    name         varchar(50),
    length       int,
    weight       float,
    registration timestamp,
    fake         boolean,
    state        varchar(50)
);