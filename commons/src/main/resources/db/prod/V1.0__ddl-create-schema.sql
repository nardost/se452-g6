-- subscriber information accepted from subscription form
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

-- account information used for authentication
create table accounts (
    id char(10) primary key,
    email varchar(64) unique not null,
    password char(60) not null,
    role varchar(16) not null
);

-- subscriptions
create table subscriptions (
    id varchar(10) primary key,
    location integer references service_locations,
    service_category varchar(64),
    activation_timestamp timestamp,
    service_status varchar(10)
);

-- service locations
create table service_locations (
    id integer primary key,
    street_address varchar(255),
    city varchar(50),
    state varchar(2),
    zip_code integer,
    unit varchar(16),
    meter_mac_address varchar(20)
);

-- bills
--CREATE TABLE Bill (
--    id SERIAL PRIMARY KEY,
--    account_number VARCHAR(10) NOT NULL,
--    billing_date DATE DEFAULT CURRENT_DATE,
--    status BOOLEAN DEFAULT FALSE
--);

-- payments
--CREATE TABLE past_payments (
--    id SERIAL PRIMARY KEY,
--    customer_id INTEGER,
--    amount_paid INTEGER,
--    credit_card_num VARCHAR(16)
--);
