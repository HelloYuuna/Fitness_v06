drop table fitness;

create table fitness
(
    usrid varchar2(20) constraint fitness_usrid_pk primary key ,                -- 회원 아이디
    usrname varchar(50) not null ,                                              -- 회원 이름
    height number(6, 2) not null ,                                              -- 회원 키
    weight number(6, 2) not null ,                                              -- 회원 몸무게
    bmi number(6,2) default 0.0,                                                -- 체질량
    bmi_result varchar2(20) check (bmi_result in ('고도비만','중도비만','경도비만', '과체중', '정상', '저체중')) , -- 체질량결과
    join_date date default sysdate                                              -- 가입 날짜
);