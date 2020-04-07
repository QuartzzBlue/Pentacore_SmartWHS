/*********스키마 생성*********/
create schema pentacore;
use pentacore;

/*********초기 테이블 삭제*********/
drop table employee;
drop table forklift;
drop table warehouse;
drop table item;
drop table forkcheck;
drop table invoice;

/*********테이블 생성*********/
create table employee(
empno varchar(10) NOT NULL,
wareid varchar(10),
warename varchar(20),
empname varchar(10),
emppw varchar(20),
empjob varchar(20),
empemail varchar(30),
empphone varchar(15),
primary key(empno),
constraint emp_ware_fk foreign key (wareid) references warehouse(wareid)
);


create table forklift(
forkid varchar(10) not null, 
wareid varchar(10), 
forkpurdate date, 
forkmodel varchar(20), 
forklastchkdate date, 
forkdist int(10) unsigned,
primary key(forkid),
constraint fork_ware_fk foreign key (wareid) references warehouse(wareid)
);


create table warehouse(
wareid varchar(10), 
warename varchar(20),
warecate varchar(20),
waresize double,
waretype varchar(20),
primary key(wareid)
); 

/**수정중**/
create table item(
itemid varchar(10), 
wareid varchar(10), 
itemname varchar(20), 
warename varchar(20), 
itemcate varchar(20), 
itemprice double, 
itemstock int(10) unsigned, 
itemweightpb double, 
itemqtypb int(5) unsigned, 
itemloc varchar(10),
primary key (itemid),
constraint item_ware_fk foreign key (wareid) references warehouse(wareid)
);


create table forkcheck(
forkchkid int unsigned auto_increment,
forkid varchar(10),
forkdist int(10) unsigned,
forkbatt tinyint(3) unsigned,
forkbrk tinyint(3) unsigned,
forklastchkdate date,
primary key(forkchkid),
constraint forkchk_fork_fk foreign key (forkid) references forklift(forkid)
);

create table invoice(
invoiceid int(10) unsigned auto_increment, 
itemid varchar(10), 
itemname varchar(20), 
wareid varchar(10), 
warename varchar(20), 
invoicestat varchar(20), 
invoiceqty int(10), 
empno varchar(10), 
empname varchar(20),
invoicedate datetime default current_timestamp,
primary key(invoiceid),
constraint invoice_item_fk foreign key (itemid) references item(itemid),
constraint invoice_ware_fk foreign key (wareid) references warehouse(wareid)
);

/*********데이터 추가*********/

/*employee*/

/*forklift*/
insert into forklift values(
'id1111', 'wh1111', now(), 'slfk-234-sgh', now(), 2400);

/*warehouse*/
insert into warehouse values(
'wh1111', '이천 제1물류창고', '가전제품', 1624.1, '일반창고'); 

/*item*/

/*forkcheck*/

/*invoice*/
