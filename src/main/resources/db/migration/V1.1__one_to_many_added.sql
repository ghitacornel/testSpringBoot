create table parent(id int primary key, name varchar(50));
create table child(id int primary key, name varchar(50), parent_id int, constraint parent_fk foreign key (parent_id) references parent(id));