create table addresses
(
    id     serial8 not null primary key,
    street text    not null,
    city   text    not null
);