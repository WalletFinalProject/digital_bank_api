CREATE TABLE IF NOT EXISTS accounts(
    id_account UUID PRIMARY KEY,
    authorize_credits boolean NOT NULL,
    creation_date date NOT NULL,
    update_date date NOT NULL,
    client_name varchar(200) NOT NULL,
    client_firstname varchar(200) NOT NULL,
    birth_date date NOT NULL CHECK (EXTRACT(YEAR FROM AGE(current_date, birth_date)) >= 21),
    net_monthly_salary double precision NOT NULL,
    id_transaction UUID REFERENCES transactions(id_transaction),
    balance double precision NOT NULL,
    credit_amount double precision NOT NULL
);

ALTER TABLE accounts
    ADD COLUMN id_account UUID DEFAULT gen_random_uuid() PRIMARY KEY;
