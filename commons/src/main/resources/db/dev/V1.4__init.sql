CREATE TABLE Bill (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(10) NOT NULL,
    billing_date DATE DEFAULT CURRENT_DATE,
    status BOOLEAN DEFAULT FALSE
);