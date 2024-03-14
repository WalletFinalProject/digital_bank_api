CREATE TABLE account(
    account_id serial PRIMARY KEY,
    client_name varchar(200) NOT NULL,
    client_firstname varchar(200) NOT NULL,
    birth_date timestamp NOT NULL,
    monthly_pay decimal(10, 2) NOT NULL,
    overdrawn_status varchar(50) NOT NULL,
    account_type varchar(50) NOT NULL
);