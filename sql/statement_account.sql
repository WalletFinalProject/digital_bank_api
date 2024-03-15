CREATE TABLE IF NOT EXISTS statement_account(
    statement_account_id serial PRIMARY KEY,
    statement_account_date TIMESTAMP NOT NULL,
    reference varchar(50) NOT NULL,
    motif varchar(50) NOT NULL,
    credit decimal(10, 2) NOT NULL,
    debit decimal(10, 2) NOT NULL,
    balance decimal(10, 2) NOT NULL,
    account_id integer REFERENCES account(account_id)
);