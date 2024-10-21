create table product
(
    customer_id  bigint      not null auto_increment,
    dtype        varchar(31) not null,
    nickname     varchar(255),
    phone_number varchar(255),
    primary key (customer_id)
) engine=InnoDB;

alter table cafe
    add constraint FKjmhmwpl2qo6olo6uu8k9wuqed
    foreign key (owner_id)
    references owner (id);
