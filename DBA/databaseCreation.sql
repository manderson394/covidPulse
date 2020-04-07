### Create Database

CREATE DATABASE covid_pulse;

### Switch to Database created

USE covid_pulse;

### Generate Tables

### Generate State FIPS Table
create table STATE_FIPS
(
    id int auto_increment,
    name varchar(255) null,
    postal_code varchar(255) null,
    fips_code int null,
    constraint STATE_FIPS_pk
        primary key (id)
);

create unique index STATE_FIPS_fips_code_uindex
    on STATE_FIPS (fips_code);

### Generate County FIPS Table
create table COUNTY_FIPS
(
    id int auto_increment,
    name varchar(255) null,
    fips_code int null,
    constraint COUNTY_FIPS_pk
        primary key (id)
);

create unique index COUNTY_FIPS_fips_code_uindex
    on COUNTY_FIPS (fips_code);

### Generate States Table

# create table STATE_COVID_DATA
# (
#     id int auto_increment,
#     date datetime null,
#     state_fips_code int null,
#     cases int null,
#     deaths int null,
#     constraint STATE_COVID_DATA_pk
#         primary key (id),
#     constraint STATE_COVID_DATA_STATE_FIPS_fips_code_fk
#         foreign key (state_fips_code) references STATE_FIPS (fips_code)
# );


### Generate Counties Tables
create table COUNTY_COVID_DATA
(
    id int auto_increment,
    date datetime null,
    county_fips_code int null,
    cases int null,
    deaths int null,
    constraint COUNTY_COVID_DATA_pk
        primary key (id),
    constraint COUNTY_COVID_DATA_COUNTY_FIPS_fips_code_fk
        foreign key (county_fips_code) references COUNTY_FIPS (fips_code)
);


