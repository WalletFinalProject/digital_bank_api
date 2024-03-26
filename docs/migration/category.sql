CREATE TABLE IF NOT EXISTS category(
    id_category UUID PRIMARY KEY,
    category_name varchar(200) NOT NULL,
    description varchar(200),
    id_transaction UUID REFERENCES transactions(id_transaction)
);