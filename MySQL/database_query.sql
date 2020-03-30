/*스키마 생성*/
create schema hexacore;
use hexacore;

/*테이블 생성*/
create table forklift(forkid varchar(10) not null, wareid varchar(10), forkpurdate date, forkmodel varchar(20), forklastcheckdate date);

/*데이터 추가*/
insert into forklift values('id1111', 'wh1111', now(), 'slfk-234-sgh', now());
