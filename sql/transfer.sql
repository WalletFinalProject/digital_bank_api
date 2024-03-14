CREATE TABLE transfer(
    transfer_id serial PRIMARY KEY,
    account_id_recipient integer NOT NULL,
    amount decimal(10, 2) NOT NULL,
    transfer_reason varchar(50) NOT NULL,
    effective_date TIMESTAMP NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    label varchar(50),
    status varchar(50) NOT NULL
);