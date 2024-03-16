CREATE TABLE IF NOT EXISTS account(
    account_id serial PRIMARY KEY,
    client_name varchar(200) NOT NULL,
    client_firstname varchar(200) NOT NULL,
    birth_date date NOT NULL,
    monthly_pay double NOT NULL,
    overdrawn_status varchar(50) NOT NULL,
    account_type varchar(50) NOT NULL
);