alter table abstract_object
    add owner_id uuid not null;

insert into users(user_id, login, password, user_roles_id)
VALUES ('9ca5d557-c71e-49db-9f90-6d263f8e3c8c', 'testAdmin', '$2a$10$coPqwg8g4AHNTeA7pLlaFOsrabmveM7kjSmzafUCjI0cj3VmGIT3u', '06a3d323-7ca8-4d09-918f-70aeb35da1b5'),
       ('1293be8b-b8b8-497c-91eb-c12e8ae41c68','testUser', '$2a$10$HXZ35hMv4zLjanL0Js9hzeAqjIeeldaO2TdPiY51//db5gVp0eUCa', '59057971-485c-4a59-9096-817b504a04c2');