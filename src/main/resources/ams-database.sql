-- CRUD - Create, Read, Update, Delete (soft/hard)
-- shortcut: ctrl+enter will run a single SQL statement
-- drops
drop table flights;

-- CREATE - Keywords: create, insert, alter
create table flights(
	flight_number integer primary key check (flight_number > 1000 and flight_number < 999999),
	origin_airport varchar(3) not null, -- foreign keys, use alter
	destination_airport varchar(3) not null,
	time_departure date,
	time_arrival date,
	seat_count smallint not null check (seat_count > 0),
	pilot integer check (pilot > 100000 and pilot < 999999),
	airline integer
); 

insert into 
	flights(flight_number, origin_airport, destination_airport, seat_count)
values (1234,'PHL','BOS',12);

insert into 
	flights(flight_number, origin_airport, destination_airport, seat_count)
values (123456,'PHL','BOS',12);

insert into 
	flights(flight_number, origin_airport, destination_airport, seat_count)
values (12345,'PHL','BOS',12);

-- READ
select * from flights;

-- UPDATE WHENVER you update make sure you have a WHERE clause
update flights
	set destination_airport= 'NYC'
	where flight_number = 1234;

select * from flights;

-- DELETE ANYTIME you use a delete make sure you have a WHERE clause
delete from flights
	where flight_number = 123456;

select * from flights;

-- specifically want to use TRUNCATE when we want to remove/delete all records in a table
truncate table flights;

select * from flights;
