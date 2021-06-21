create table PLAYER
(
    id integer not null
        constraint CONTACT_pk
            primary key autoincrement,
    name text not null,
    pos text not null,
    birth_year integer not null,
    team_id integer
        references TEAM
);

create table TEAM
(
    id integer not null
        constraint TEAM_pk
            primary key autoincrement,
    name text not null,
    successes text,
    nationality text not null
);

create table GAME
(
    id integer not null
        constraint GAME_pk
            primary key autoincrement,
    name text not null,
    game_date text not null,
    referee text,
    location text,
    home_team integer
        references TEAM,
    away_team integer
        references TEAM,
    travel_info text
);