-- subscribers
-- exists in accounts module
-- information accepted from subscription form
create table subscribers (
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

-- accounts
-- exists in accounts module
-- information used for authentication
create table accounts (
    id varchar(10) primary key,
    email varchar(64) unique not null,
    password char(60) not null,
    role varchar(16) not null
);

-- service locations
-- exists in facilities module
-- locations where service is installed
create table service_locations (
    id integer primary key,
    street_address varchar(255),
    city varchar(50),
    state varchar(2),
    zip_code integer,
    unit varchar(16),
    meter_mac_address varchar(20)
);

-- subscriptions
-- exists in facilities module
-- customer subscription information
create table subscriptions (
    id varchar(10) primary key,
    location integer references service_locations,
    service_category varchar(64),
    activation_timestamp timestamp,
    service_status varchar(10)
);

-- bills
-- exists in billing module
-- billing information
create table bills (
    id serial primary key,
    account_number varchar(10) not null,
    amount integer not null,
    billing_date date default current_date,
    due_date date not null,
    paid boolean default false
);

-- payments
-- exists in payment module
-- payment information
create table payments (
    id serial primary key,
    bill_id integer not null,
    amount integer not null,
    credit_card_number varchar(16) not null,
    cvv integer not null,
    name_on_card varchar(255) not null,
    street_address varchar(255) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip_code integer not null,
    unit varchar(16),
    transaction_time timestamp not null,
    approved boolean default false
);