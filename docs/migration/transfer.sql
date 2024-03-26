CREATE TABLE IF NOT EXISTS transfer(
    id_transfer UUID PRIMARY KEY,
    id_account UUID REFERENCES accounts(id_account),
    amount double precision NOT NULL,
    transfer_reason varchar(200) NOT NULL,
    effective_date date NOT NULL,
    registration_date date NOT NULL,
    label varchar(200),
    status varchar(200) NOT NULL,
    id_balance_history UUID REFERENCES balance_history(id_balance_history)
);