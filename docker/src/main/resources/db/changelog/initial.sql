create table person
(
    id            integer generated always as identity primary key,
    name          varchar(50),
    date_of_birth timestamp
);