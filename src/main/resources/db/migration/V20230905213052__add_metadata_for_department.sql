alter table departments
    add metadata jsonb default '{}'::jsonb not null;