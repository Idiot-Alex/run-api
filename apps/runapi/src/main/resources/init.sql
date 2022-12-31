
create table if not exists `PUBLIC`.`api_log`(
    `id` bigint not null primary key ,
    `url` varchar(255) not null,
    `method` varchar(10) null,
    `code` varchar(5) not null,
    `msg` varchar(255) null,
    `headers` CLOB null,
    `body` CLOB null,
    `res` CLOB null
);