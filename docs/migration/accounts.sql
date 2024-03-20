CREATE TABLE IF NOT EXISTS accounts(
    id_account serial PRIMARY KEY,
    authorize_credits boolean NOT NULL,
    creation_date date NOT NULL,
    update_date date NOT NULL,
    client_name varchar(200) NOT NULL,
    client_firstname varchar(200) NOT NULL,
    birth_date date NOT NULL CHECK (EXTRACT(YEAR FROM AGE(current_date, birth_date)) >= 21),
    net_monthly_salary decimal(10, 2) NOT NULL,
    id_transaction integer REFERENCES transactions(id_transaction)
);
