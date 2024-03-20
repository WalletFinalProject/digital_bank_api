CREATE TABLE IF NOT EXISTS transfer(
    id_transfer serial PRIMARY KEY,
    id_account integer REFERENCES accounts(id_account),
    amount decimal(10, 2) NOT NULL,
    transfer_reason varchar(200) NOT NULL,
    effective_date date NOT NULL,
    registration_date date NOT NULL,
    label varchar(200),
    status varchar(200) NOT NULL,
    id_balance_history integer REFERENCES balance_history(id_balance_history)
);