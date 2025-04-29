CREATE TABLE IF NOT EXISTS users
(
    id
    BIGINT
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    country_id BIGINT NOT NULL,
    CONSTRAINT fk_user_country FOREIGN KEY
(
    country_id
) REFERENCES countries
(
    id
)
    );

INSERT INTO Users (name, country_id)
VALUES
    ('John Doe', 1),  -- Assuming country_id 1 is 'USA'
    ('Jane Smith', 2),  -- Assuming country_id 2 is 'Canada'
    ('Sam Wilson', 3),  -- Assuming country_id 3 is 'Germany'
    ('Emily Davis', 4),  -- Assuming country_id 4 is 'France'
    ('Michael Brown', 5);  -- Assuming country_id 5 is 'Australia'