CREATE TABLE IF NOT EXISTS transaction(
    transaction_id serial PRIMARY KEY,
    transaction_date TIMESTAMP NOT NULL,
    amount Double NOT NULL,
    transaction_type varchar(50) CHECK (transaction_type IN ('debit', 'credit')),
    account_id integer REFERENCES account(account_id)
);
