-- service locations
create table service_locations (
    id serial primary key,
    street_address varchar(255),
    city varchar(50),
    state varchar(2),
    zip_code integer,
    unit varchar(16),
    meter_mac_address varchar(20)
);

-- subscriptions
create table subscriptions (
    id varchar(10) primary key,
    location_id integer references service_locations,
    service_category varchar(20),
    activation_timestamp timestamp,
    service_status varchar(10)
);