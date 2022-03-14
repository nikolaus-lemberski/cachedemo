drop table if exists TASKS;

create table TASKS (
  id varchar(36) not null,
  description varchar(255),
  done boolean,
  primary key (id)
);