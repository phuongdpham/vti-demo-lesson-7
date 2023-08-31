alter table addresses add created_at timestamptz default now();
alter table addresses add updated_at timestamptz default now();

alter table addresses add department_id bigint not null references departments(id) on delete cascade;
