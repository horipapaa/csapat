create table MESSAGE
(
    id integer not null
        constraint MESSAGE_pk
            primary key autoincrement,
    from_player int not null,
    to_player int not null,
    team_id int not null
        constraint MESSAGE_TEAM_id_fk
            references TEAM,
    content text not null
);

CREATE TABLE GAME
(
    id integer not null
        constraint GAME_pk
            primary key autoincrement,
    game_date text not null,
    referee text,
    location text,
    home_team integer
        references TEAM,
    away_team integer
        references TEAM,
    travel_info text,
    completed boolean default false not null,
    result text
);