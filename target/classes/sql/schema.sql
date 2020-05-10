CREATE TABLE weatherdata(
    id IDENTITY PRIMARY KEY,
    city VARCHAR(255),
    date_measured DATE,
    temperature_min DOUBLE,
    temperature_max DOUBLE
);