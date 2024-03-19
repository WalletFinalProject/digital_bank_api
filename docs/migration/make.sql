CREATE TABLE IF NOT EXISTS make(
    account_id integer REFERENCES account(account_id),
    transfer_id integer REFERENCES transfer(transfer_id),
    PRIMARY KEY (transfer_id, account_id)
);
