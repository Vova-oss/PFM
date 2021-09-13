create schema if not exists pfm;


create table if not exists pfm.roles
(
    id   bigserial not null,
    role text      not null default 'USER',
    constraint roles_pk primary key (id)
);


create table if not exists pfm.users
(
    id       bigserial not null,
    login    text,
    password text,
    role_id  bigint,
    constraint users_pk primary key (id),
    constraint users_role_fk foreign key (role_id) references pfm.roles (id) on delete restrict
);


create table if not exists pfm.users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint users_roles_users_fk foreign key (user_id) references pfm.users (id) on delete cascade,
    constraint users_roles_roles_fk foreign key (role_id) references pfm.roles (id) on delete restrict
);


create table if not exists pfm.mcc
(
    code       bigserial not null,
    info       text      not null,
    group_code text      not null,
    constraint mcc_pk primary key (code)
);


create table if not exists pfm.refresh_tokens
(
    id          bigserial not null,
    user_id     bigint    not null,
    token       text      not null,
    expiry_date text      not null,
    constraint refresh_tokens_pk primary key (id),
    constraint refresh_tokens_users_fk foreign key (user_id) references pfm.users (id) on delete cascade
);


create table if not exists pfm.transaction_data
(
    id            bigserial not null,
    card_id       bigint    not null,
    score_of_card text      not null,
    date          text      not null,
    sum           text      not null,
    currency      text      not null,
    info          text      not null,
    mcc_info      text,
    mcc_code      bigint,
    user_id       bigint    not null,
    constraint transaction_data_pk primary key (id),
    constraint transaction_data_mcc_fk foreign key (mcc_code) references pfm.mcc (code) on delete set null,
    constraint transaction_data_users_fk foreign key (user_id) references pfm.users (id) on delete cascade
);