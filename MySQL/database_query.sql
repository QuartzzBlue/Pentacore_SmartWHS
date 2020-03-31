/***스키마 생성***/
create schema pentacore;
use pentacore;

/***테이블 생성***/
create table forklift(forkid varchar(10) not null, wareid varchar(10), forkpurdate date, forkmodel varchar(20), forklastcheckdate date, forkdist int(1));
create table item(itemid varchar(10), wareid varchar(10), itemname varchar(30), warename varchar(30), itemcate varchar(30), itemprice double, itemstock int(1), itemweightpb int(1), itemquantitypb int(1), itemloc varchar(10));
create table warehouse(wareid varchar(10), warename varchar(30), warecate varchar(30), warescale double, waretype varchar(10));

/***데이터 추가***/
/*forklift*/
insert into forklift values('id1111', 'wh1111', now(), 'slfk-234-sgh', now(), 2400);
/*warehouse*/
insert into warehouse values('wh1111', '이천 제1물류창고', '가전제품', 1624.1, '일반창고'); 