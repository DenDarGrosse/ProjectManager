create table abstract_object
(
    id                 uuid primary key not null,
    parent_id          uuid,
    dtype              varchar(255)     not null,
    object_name        varchar(255)     not null,
    created_date       date             not null,
    last_modified_date date             not null
);

drop table Task;
drop table project;

create table project
(
    id uuid primary key not null
);

create table task
(
    id          uuid primary key not null,
    task_status integer          not null,
    task_type   integer          not null
);