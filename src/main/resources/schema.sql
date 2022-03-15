drop table if exists TASKS;

create table TASKS (
  id uuid default random_uuid(),
  description varchar(255),
  done boolean,
  primary key (id)
);