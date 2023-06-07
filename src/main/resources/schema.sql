DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS STATISTICS;
DROP TABLE IF EXISTS GAME_SESSION;

create table if not exists USERS (
    id                identity      primary key,
    username          varchar(50)   not null,
    password          varchar(100)  not null,
    email             varchar(50)   not null,
    algorithm         varchar(20)   not null,
    registration_date date          not null
    );

create table if not exists STATISTICS (
    id                  identity    primary key,
    all_time_play_time  bigint      not null,
    all_time_score      bigint      not null,
    fastest_win         bigint      not null,
    max_score           bigint      not null,
    user_id             bigint      not null
        references Users
    );

create table if not exists GAME_SESSION (
    id                  identity    primary key,
    get_started         timestamp   not null,
    duration            bigint      not null,
    score               bigint      not null,
    statistics_id       bigint      not null
        references Statistics
    );