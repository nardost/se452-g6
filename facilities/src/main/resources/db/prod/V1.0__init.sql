CREATE TABLE service_locations (
    id SERIAL PRIMARY KEY,
    street_address VARCHAR(255) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code INTEGER NOT NULL,
    unit VARCHAR(16)
);