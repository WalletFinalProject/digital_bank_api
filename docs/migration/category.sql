CREATE TABLE IF NOT EXISTS category(
    id_category serial PRIMARY KEY,
    category_name varchar(200) NOT NULL,
    description varchar(200),
    id_transaction integer REFERENCES transactions(id_transaction)
);