CREATE TABLE service_locations (
    id SERIAL PRIMARY KEY,
    street_address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(2),
    zip_code INTEGER,
    unit VARCHAR(16)
);