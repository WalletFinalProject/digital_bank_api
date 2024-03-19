CREATE TABLE IF NOT EXISTS transfer(
    transfer_id serial PRIMARY KEY,
    account_id_recipient integer NOT NULL,
    amount Double NOT NULL,
    transfer_reason varchar(50) NOT NULL,
    effective_date TIMESTAMP NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    label varchar(50),
    status varchar(50) NOT NULL
);