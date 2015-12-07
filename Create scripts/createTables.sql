drop table Reviews;
drop table Users;
drop table Restaurant;

CREATE TABLE Users (

username VARCHAR(30),
password VARCHAR(255),
PRIMARY KEY (`username`)
);

CREATE TABLE RESTAURANT(
	id int,
	name VARCHAR(30),
    address VARCHAR(30),
    city VARCHAR(30),
    state CHAR(2),
    zip CHAR(5),
    latitude double,
    longitude double,
    primary key (`id`)
);

CREATE TABLE REVIEWS(
	user VARCHAR(30),
    restaurantID int,
    rating double,
    constraint pk_PersonID PRIMARY KEY (user,restaurantID) 
);