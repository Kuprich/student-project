drop table if exists cr_address_person;
drop table if EXISTS cr_address;
drop table if EXISTS cr_district;
drop table if EXISTS cr_street;
drop table if exists cr_person;


CREATE TABLE cr_district (
	district_code integer not null,
	district_name varchar(300),
	primary key (district_code)
);

CREATE TABLE cr_street (
	street_code integer not null,
	street_name varchar(300),
	primary key (street_code)
);

CREATE TABLE cr_address (
	address_id serial,
	district_code integer not null,
	street_code integer not null,
	building varchar(10) not null,
	extension varchar(10),
	apartment varchar(10),
	primary key (address_id),
	foreign key (district_code) REFERENCES cr_district(district_code) on delete restrict,
	foreign key (street_code) REFERENCES cr_street(street_code) on delete restrict
);

CREATE TABLE cr_person (
	person_id serial,
	sur_name           VARCHAR(100)    NOT NULL,
    given_name         VARCHAR(100)    NOT NULL,
    patronymic         VARCHAR(100)    NOT NULL,
    date_of_birth      DATE            NOT NULL,
    passport_seria     VARCHAR(10),
    passport_number    VARCHAR(10),
    passport_date      DATE,
	certificate_number VARCHAR(10),
    certificate_date   DATE,
	primary key (person_id)
);

CREATE TABLE cr_address_person (
	person_address_id serial,
	address_id integer not null,
	person_id integer not null,
	start_date date not null,
	end_date date,
	temporal boolean default false,
	primary key (person_address_id),
	foreign key (address_id) REFERENCES cr_address(address_id) on delete restrict,
	foreign key (person_id) REFERENCES cr_person(person_id) on delete restrict
);



