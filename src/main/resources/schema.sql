create sequence if not exists hibernate_sequence start with 1 increment by 1;

create table if not exists customer_file
(
    ID                int primary key auto_increment,
    client            varchar(255),
    created_date_time timestamp,
    reference         varchar(255)
);

create table if not exists customer_message
(
    id                int primary key auto_increment,
    channel           varchar(255),
    content           varchar(255),
    created_date_time timestamp,
    customer_file_id  bigint,
    sender            varchar(255)
);

create table if not exists customer_file_message
(
    customer_file_id    int not null,
    customer_message_id int not null unique
);