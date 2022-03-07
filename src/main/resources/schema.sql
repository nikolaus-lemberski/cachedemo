drop table if exists TASKS;

create table TASKS (
  id integer identity primary key,
  description varchar(255),
  done boolean
);