insert into pfm.users (login, password)
    (SELECT generate_series(1, 479), '$2a$12$4oXol.yq7X/dTcoJ0BTzwOMTRP8xM2PSi0wWrc18Fg3Jf7iCCaplm');

insert into pfm.roles (role)
values ('USER'),
       ('ADMIN');

insert into pfm.users_roles(user_id, role_id)
        (select generate_series(1, 479), 1);
