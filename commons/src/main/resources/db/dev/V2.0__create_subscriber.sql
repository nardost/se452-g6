-- subscriber information accepted from subscription form
create table subscriber (
    id varchar(10) primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(64) not null,
    street_address varchar(255) not null,
    unit varchar(16),
    city varchar(50) not null,
    state varchar(2) not null,
    zip_code integer not null,
    credit_card_number varchar(16) not null,
    cvv varchar(4) not null,
    expiration_mm varchar(2) not null,
    expiration_yyyy varchar(4) not null,
    service_type varchar(50) not null
);