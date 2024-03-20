CREATE TABLE IF NOT EXISTS bank(
    id_bank serial PRIMARY KEY,
    name varchar(200) NOT NULL,
    creation_date date NOT NULL,
    update_date date NOT NULL,
    id_transfer integer REFERENCES transfer(id_transfer)
);