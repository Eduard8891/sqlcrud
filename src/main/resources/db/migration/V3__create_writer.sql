drop table if exists writer;
create table if not exists writer
(
    id         serial4 primary key,
    first_name varchar(100),
    last_name  varchar(100),
    status     varchar(25)
)