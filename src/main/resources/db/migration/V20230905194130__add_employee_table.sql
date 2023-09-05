create table employee
(
    id         serial8      not null primary key,
    name       varchar(255) not null,
    email      varchar(255) not null,
    created_at timestamptz  not null default now(),
    updated_at timestamptz  not null default now()
);

create index employee_name_idx on employee (name);
create unique index employee_email_idx on employee (email);

create table employee_departments_detail
(
    employee_id   int8 not null references employee (id) on delete cascade,
    departments_id int8 not null references departments (id) on delete cascade
);