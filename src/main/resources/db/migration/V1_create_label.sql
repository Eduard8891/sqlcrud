drop table if exists label;
create table if not exists label
(
    id         serial4 primary key,
    label_name varchar(100),
    status     varchar(25)
);