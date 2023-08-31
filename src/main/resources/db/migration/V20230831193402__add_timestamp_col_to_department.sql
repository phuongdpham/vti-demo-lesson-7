alter table departments add created_at timestamptz default now();
alter table departments add updated_at timestamptz default now();
