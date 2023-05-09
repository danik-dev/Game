create table if not exists Users (
    id identity,
    username varchar(50),
    password varchar(50),
    email varchar(50),
    algorithm varchar(20),
    registrationDate date
);

create table if not exists Statistics (
    id identity,
    allTimePlayTime bigint,
    allTimeScore bigint,
    fastestWin bigint,
    user_id bigint
);

create table if not exists GameSession (
    id identity,
    getStarted datetime,
    duration bigint,
    score bigint,
    statistics_id bigint
);

alter table Statistics
    add foreign key (user_id) references Users;

alter table GameSession
    add foreign key (statistics_id) references Statistics;



