-- alter the location_id column of subscription to location
alter table subscriptions rename column location_id to location;