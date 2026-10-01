create table panier (
    id serial primary key,
    client_id integer not null unique references user_(id)
);

create table commande (
    id serial primary key,
    client_id integer not null references user_(id),
    date_commande timestamp not null
);

alter table pierre add column panier_id integer references panier(id);
alter table pierre add column commande_id integer references commande(id);
