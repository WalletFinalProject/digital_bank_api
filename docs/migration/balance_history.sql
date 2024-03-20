CREATE TABLE IF NOT EXISTS balance_history(
    id_balance_history serial PRIMARY KEY,
    principal_amount decimal(10, 2) NOT NULL,
    money_loan decimal(10, 2) NOT NULL,
    loan_interest decimal(10, 2) NOT NULL
);