ALTER TABLE account
ALTER COLUMN birth_date TYPE date;

ALTER TABLE account
ALTER COLUMN monthly_pay TYPE double precision;

ALTER TABLE account
ADD CONSTRAINT check_age CHECK (check_age_constraint(birth_date));