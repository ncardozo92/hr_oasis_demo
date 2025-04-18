
drop table app_user


create table app_user(
    id IDENTITY PRIMARY KEY,
    name varchar,
    email varchar,
    password varchar,
    token varchar,
    created date,
    modified date,
    last_login date
);



create table phone(
    id IDENTITY PRIMARY KEY,
    id_user bigint,
    number varchar,
    citycode varchar,
    countrycode varchar,
    foreign key (id_user) references app_user(id)
);