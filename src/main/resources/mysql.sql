create database webflux_learn;

use webflux_learn;

create table user
(
    id        varchar(32) not null
        primary key,
    name      varchar(64) null,
    age       int         null,
    gender    varchar(2)  null,
    phone_num varchar(11) null
) engine = InnoDB,
  charset = utf8;