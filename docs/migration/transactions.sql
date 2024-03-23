    CREATE TABLE IF NOT EXISTS transactions(
        id_transaction serial PRIMARY KEY,
        transaction_date TIMESTAMP NOT NULL,
        amount double precision NOT NULL,
        transaction_type varchar(200) CHECK (transaction_type IN ('debit', 'credit')),
        label varchar(200)
    );
