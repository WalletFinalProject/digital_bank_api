CREATE TABLE IF NOT EXISTS balance_history(
    balance_history_id serial PRIMARY KEY,
    principal_amount decimal(10, 2) NOT NULL,
    money_loan decimal(10, 2) NOT NULL,
    loan_interest decimal(10, 2) NOT NULL,
    account_id integer REFERENCES account(account_id)
);