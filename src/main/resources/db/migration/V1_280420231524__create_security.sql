create table users
(
    user_id  uuid         not null
        primary key,
    login    varchar(255) not null,
    password varchar(255) not null
);

create table roles
(
    role_id   uuid        not null
        primary key,
    role_name varchar(32) not null unique
);

ALTER TABLE users
    ADD COLUMN user_roles_id uuid;
ALTER TABLE users
    ADD CONSTRAINT fk_users_roles FOREIGN KEY (user_roles_id) REFERENCES roles (role_id);

insert into roles(role_id, role_name)
VALUES ('06a3d323-7ca8-4d09-918f-70aeb35da1b5', 'ADMIN'),
       ('59057971-485c-4a59-9096-817b504a04c2', 'USER')