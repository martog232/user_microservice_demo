CREATE TABLE IF NOT EXISTS countries
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
    100
) NOT NULL,
    code VARCHAR
(
    3
) NOT NULL
    );

INSERT INTO countries (name, code)
VALUES ('United States', 'USA'),
       ('Canada', 'CAN'),
       ('Germany', 'DEU'),
       ('France', 'FRA'),
       ('Japan', 'JPN'),
       ('Australia', 'AUS'),
       ('Brazil', 'BRA'),
       ('India', 'IND'),
       ('China', 'CHN'),
       ('Russia', 'RUS');