CREATE TABLE IF NOT EXISTS balance_history(
    id_balance_history serial PRIMARY KEY,
    principal_amount double precision NOT NULL,
    money_loan double precision NOT NULL,
    loan_interest double precision NOT NULL
);