create table persons
(
    id            integer generated always as identity primary key,
    name          varchar(50),
    register_date timestamp
);