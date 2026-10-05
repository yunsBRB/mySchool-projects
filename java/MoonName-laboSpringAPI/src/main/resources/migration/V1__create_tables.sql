create table user_ (
    id serial primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    role varchar(20) not null
);

create table mission (
    id serial primary key,
    nom varchar(100) not null,
    date_depart date not null
);

create table pierre (
    id serial primary key,
    inscription varchar(40) not null,
    pays varchar(40) not null,
    statut varchar(20) not null,
    mission_id integer not null references mission(id),
    client_id integer not null references user_(id)
);
