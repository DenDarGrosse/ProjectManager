create table project
(
    id                 uuid primary key not null,
    parent_id          uuid,
    project_name       varchar(255)     not null,
    created_date       date             not null,
    last_modified_date date             not null
);

create table Task
(
    id                 uuid primary key not null,
    parent_id          uuid             not null,
    task_type          integer          not null,
    task_status        integer          not null,
    task_name          varchar(255)     not null,
    created_date       date             not null,
    last_modified_date date             not null
);