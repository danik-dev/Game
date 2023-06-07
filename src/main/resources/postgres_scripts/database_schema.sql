create database game;

create table users
(
    id                serial
        primary key,
    username          varchar(50)  not null,
    password          varchar(100) not null,
    email             varchar(50)  not null,
    algorithm         varchar(20)  not null,
    registration_date date         not null
);

alter table users
    owner to postgres;

create table statistics
(
    id                 serial
        primary key,
    all_time_play_time bigint not null,
    all_time_score     bigint not null,
    fastest_win        bigint not null,
    max_score          bigint not null,
    user_id            bigint not null
        references users
);

alter table statistics
    owner to postgres;

create unique index statistics_user_id_uindex
    on statistics (user_id);

alter table game_session
    owner to postgres;

create table game_session
(
    id            serial
        primary key,
    get_started   timestamp                                               not null,
    duration      bigint                                                  not null,
    score         bigint                                                  not null,
    statistics_id bigint                                                  not null
            references statistics
);

create unique index game_session_get_started_uindex
    on game_session (get_started);




