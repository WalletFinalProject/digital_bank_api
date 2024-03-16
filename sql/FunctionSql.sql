CREATE OR REPLACE FUNCTION check_age_constraint(birth_date date)
RETURNS BOOLEAN AS $$
BEGIN
RETURN birth_date <= current_date - interval '21 years';
END;
$$ LANGUAGE plpgsql;