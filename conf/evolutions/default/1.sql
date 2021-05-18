# --- Todo schema

# --- !Ups

set ignorecase true;

create sequence todo_id_seq;
create table todos (
                       id                    integer not null default nextval('todo_id_seq'),
                       label                 varchar(255) not null
);

insert into todos (id, label) values (  1,'투두리스트 만들기');
insert into todos (id, label) values (  2,'스칼라 어렵다');
# --- !Downs

drop table todos;
drop sequence todo_id_seq;