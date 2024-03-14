CREATE TABLE category(
    category_id serial PRIMARY KEY,
    category_name varchar(50) NOT NULL,
    transaction_id integer REFERENCES transaction(transaction_id)
);