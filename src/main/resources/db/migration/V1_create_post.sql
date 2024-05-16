drop table if exists post;
create table if not exists post
(
    id           serial4 primary key,
    post_content varchar(500),
    created      timestamp,
    updated      timestamp,
    status       varchar(25)
)