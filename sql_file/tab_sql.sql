/*
	sql test
*/
create database twst;

use twst;

create table IF NOT EXISTS `teacher`(
	`id` int(11) primary key auto_increment,
	`tea_id` int(11) not null,
	`tea_name` varchar(30) not null,
	`tea_tel` varchar(30)
);

create table IF NOT EXISTS `student`(
	`id` int(11) primary key auto_increment,
	`stu_id` int(11) not null,
	`tea_id` int(11) not null,
	`stu_name` varchar(30) not null,
	`stu_tel` varchar(30)
);