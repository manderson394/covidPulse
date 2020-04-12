### Create Database

CREATE DATABASE covid_pulse;

### Switch to Database created

USE covid_pulse;

### Generate Tables

### Generate County FIPS Table
create table COUNTY_FIPS
(
    name varchar(255) null,
    fips_code int not null,
    state char(2),
    constraint COUNTY_FIPS_pk
        primary key (fips_code)
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


