--Account Management table
CREATE TABLE account_management (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(40),
    password VARCHAR(50)
);

--Employee Management
CREATE TABLE employee_management (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    department VARCHAR(255),
    email VARCHAR(40),
    password VARCHAR(50)
);