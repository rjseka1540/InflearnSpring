//Member DB 생성
drop table if exists member CASCADE;
create table member
(
    -- generated by default as identity : id(PK) 가 없을 시 채워넣음
    -- (MySQL) NOT NULL AUTO_INCREMENT PRIMARY KEY 와 같음
    id bigint generated by default as identity,
    name varchar(255),
    primary key(id)
);