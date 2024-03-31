CREATE TABLE account_statement(
                                  id_account_statement UUID PRIMARY KEY,
                                  account_statement_date TIMESTAMP NOT NULL,
                                  reference varchar(200) NOT NULL,
                                  motif varchar(50) NOT NULL,
                                  credit_MGA double precision NOT NULL,
                                  debit_MGA double precision NOT NULL,
                                  balance double precision NOT NULL,
                                  id_account UUID REFERENCES accounts(id_account)
);