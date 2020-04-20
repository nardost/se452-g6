-- This will not be done in production
DROP TABLE IF EXISTS service_locations;
CREATE TABLE service_locations (
    id SERIAL PRIMARY KEY,
    street_address VARCHAR(255),
    unit VARCHAR(10),
    city VARCHAR(50),
    state VARCHAR(2),
    zip_code INTEGER
);
INSERT INTO service_locations (street_address, unit, city, state, zip_code) VALUES
    ('1 E JACKSON BLVD', '', 'Chicago', 'IL', 60604),
    ('320 N MICHIGAN AVE', '', 'Chicago', 'IL', 60601),
    ('1000 N STATE ST', '', 'Chicago', 'IL', 60610),
    ('100 W LAKE ST', '', 'Chicago', 'IL', 60601),
    ('1530 W FOSTER AVE', '', 'Chicago', 'IL', 60640),
    ('6000 N CLARK ST', '', 'Chicago', 'IL', 60660),
    ('2000 W DEVON AVE', '', 'Chicago', 'IL', 60659);