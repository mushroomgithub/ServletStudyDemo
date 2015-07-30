//数据库和表设计：
create database dbXFFS character set utf8 collate utf8_general_ci;
use dbXFFS;

//用户表设计
create table users
(
	id varchar(40) primary key,
	username varchar(40) not null,
	password varchar(20) not null
);

insert into users(id,username,password) values('1','admin','admin');
insert into users(id,username,password) values('2','mushroom','123456');

//主界面表设计
create table mainface
(
	id varchar(40) primary key,
	btnname varchar(500) not null,
	btnid varchar(40) not null
);
ALTER TABLE mainface ADD unique('btnid');
insert into mainface(id,btnname,btnid) values ('01','基本生命支持','1000');
insert into mainface(id,btnname,btnid) values ('02','基本生命支持训练','1004');
insert into mainface(id,btnname,btnid) values ('03','基本生命支持模拟考核训练','1005');
insert into mainface(id,btnname,btnid) values ('04','基本生命支持考核','1006');
insert into mainface(id,btnname,btnid) values ('05','心肺复苏竞赛','1007');

//基本生命支持(01)
create table bls
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into bls(id,btnname,btnid) values ('011','讲解','5');
insert into bls(id,btnname,btnid) values ('012','向前','8');
insert into bls(id,btnname,btnid) values ('013','向后','9');
insert into bls(id,btnname,btnid) values ('014','上级菜单','7');
insert into bls(id,btnname,btnid) values ('015','主菜单','6');
insert into bls(id,btnname,btnid) values ('016','控制台','10');
insert into bls(id,btnname,btnid) values ('017','关闭','3');

//基本生命支持训练（02）
create table blse
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blse(id,btnname,btnid) values ('021','开始训练','204');
insert into blse(id,btnname,btnid) values ('022','复位','205');
insert into blse(id,btnname,btnid) values ('023','记忆回放','206');
insert into blse(id,btnname,btnid) values ('024','停止回放','209');
insert into blse(id,btnname,btnid) values ('025','关闭','203');

//基本生命支持模拟考核训练(03)
create table blsmnkhe
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blsmnkhe(id,btnname,btnid) values ('031','开始考核','204');
insert into blsmnkhe(id,btnname,btnid) values ('032','交卷','205');
insert into blsmnkhe(id,btnname,btnid) values ('033','国际新标准','206');
insert into blsmnkhe(id,btnname,btnid) values ('034','自定义标准','207');
insert into blsmnkhe(id,btnname,btnid) values ('035','记忆回放','208');
insert into blsmnkhe(id,btnname,btnid) values ('036','停止回放','209');
insert into blsmnkhe(id,btnname,btnid) values ('037','关闭','210');

//基本生命支持考核(04)
create table blskh
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blskh(id,btnname,btnid) values ('041','开始考核','404');
insert into blskh(id,btnname,btnid) values ('042','交卷','405');
insert into blskh(id,btnname,btnid) values ('043','国际新标准','407');
insert into blskh(id,btnname,btnid) values ('044','自定义标准,'408');
insert into blskh(id,btnname,btnid) values ('045','记忆回放','409');
insert into blskh(id,btnname,btnid) values ('046','停止回放','410');
insert into blskh(id,btnname,btnid) values ('047','关闭','403');

//交卷（042）
create table blskh042
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blskh042(id,btnname,btnid) values ('0421','保存','323');
insert into blskh042(id,btnname,btnid) values ('0422','关闭','324');


//国际新标准（043）
create table blskh043
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blskh043(id,btnname,btnid) values ('0431','确定','313');

//自定义标准(044)
create table blskh044
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into blskh044(id,btnname,btnid) values ('0441','保存','318');
insert into blskh044(id,btnname,btnid) values ('0442','取消','319');

//心肺复苏竞赛(05)

//心肺复苏页面1表设计
create table xffsface1
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into xffsface1(id,btnname,btnid) values ('11','开始','506');
insert into xffsface1(id,btnname,btnid) values ('12','返回','504');

//心肺复苏页面2表设计
create table xffsface2
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into xffsface2(id,btnname,btnid) values ('21','交卷','405');
insert into xffsface2(id,btnname,btnid) values ('22','返回','424');
insert into xffsface2(id,btnname,btnid) values ('23','国际新标准','407');
insert into xffsface2(id,btnname,btnid) values ('24','记忆回放','409');
insert into xffsface2(id,btnname,btnid) values ('25','停止回放','410');

//心肺复苏页面3表设计
create table xffsface3
(
	id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
);

insert into xffsface3(id,btnname,btnid) values ('31','确定','313');

#基于web的医疗模拟人系统数据库设计
