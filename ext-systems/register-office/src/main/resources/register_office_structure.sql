drop table if exists ro_passport;
drop table if exists ro_person;


create table ro_person (
	person_id serial,
	gender smallint not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	patronymic varchar(100) not null,
	date_of_birth date not null,
	primary key (person_id)
);

create table ro_passport (
    passport_id serial,
    person_id integer not null,
    seria varchar(100) not null,
    number varchar(100) not null,
    issue_date date not null,
    issue_department varchar(100) not null,
    primary key (passport_id),
    foreign key (person_id) references ro_person(person_id) on delete restrict
);