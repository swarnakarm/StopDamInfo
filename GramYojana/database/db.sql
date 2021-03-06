create database StopDAM;
create user 'user'@localhost identified by 'password';
grant all privileges on StopDAM.* to 'user'@localhost;
use StopDAM;
create table state(state_code bigint, state_name varchar(256), primary key (state_code));
create table district(district_code bigint, district_name varchar(256), primary key (district_code));
create table block(block_code bigint, block_name varchar(256), primary key (block_code));
create table panchayat(panchayat_code bigint, panchayat_name varchar(256), primary key (panchayat_code));
create table village(village_code bigint, village_name varchar(256), primary key (village_name));
create table block_panchayat(block_code bigint references block(block_code),panchayat_code bigint references panchayat(panchayat_code));
create table district_block(district_code bigint references district(district_code), block_code bigint references block(block_code));
create table panchayat_village(panchayat_code bigint references panchayat(panchayat_code), village_code bigint references village(village_code));
create table state_district(state_code bigint references state(state_code), district_code bigint references district(district_code));
create table gateInfo(id bigint unsigned NOT NULL AUTO_INCREMENT primary key,village_code bigint references village(village_code), open_date datetime, close_date datetime);
