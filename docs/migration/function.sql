CREATE OR REPLACE FUNCTION transfer_money(
    id_account_from UUID,
    id_account_to UUID,
    amount DOUBLE PRECISION,
    transfer_reason VARCHAR(200)
) RETURNS VOID AS $$
DECLARE
account_from_balance DOUBLE PRECISION;
    account_to_balance DOUBLE PRECISION;
BEGIN

    IF NOT EXISTS (SELECT 1 FROM accounts WHERE id_account = id_account_from AND authorize_credits = TRUE) THEN
        RAISE EXCEPTION 'The source account is not authorized to receive credits.';
END IF;
    IF NOT EXISTS (SELECT 1 FROM accounts WHERE id_account = id_account_to AND authorize_credits = TRUE) THEN
        RAISE EXCEPTION 'The destination account is not authorized to receive credits.';
END IF;


SELECT balance INTO account_from_balance FROM accounts WHERE id_account = id_account_from;
SELECT balance INTO account_to_balance FROM accounts WHERE id_account = id_account_to;


IF account_from_balance < amount THEN
        RAISE EXCEPTION 'Insufficient balance on source account.';
END IF;


UPDATE accounts SET balance = balance - amount WHERE id_account = id_account_from;
UPDATE accounts SET balance = balance + amount WHERE id_account = id_account_to;


INSERT INTO transfer(id_account, amount, transfer_reason, effective_date, registration_date, status)
VALUES (id_account_from, amount, transfer_reason, CURRENT_DATE, CURRENT_DATE, 'Done');


UPDATE accounts SET update_date = CURRENT_DATE WHERE id_account IN (id_account_from, id_account_to);
END;
$$ LANGUAGE plpgsql;
