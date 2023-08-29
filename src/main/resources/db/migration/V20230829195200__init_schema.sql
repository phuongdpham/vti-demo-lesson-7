create table departments
(
    id          serial8      not null primary key,
    name        varchar(255) not null,
    description text         not null
);

create index idx_departments_name on departments (name);