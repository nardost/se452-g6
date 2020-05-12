CREATE TABLE past_payments (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    amount_paid INTEGER,
    credit_card_num INTEGER
);