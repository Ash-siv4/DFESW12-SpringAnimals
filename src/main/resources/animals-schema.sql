drop table if exists animals CASCADE;
CREATE TABLE animals (
    id BIGINT AUTO_INCREMENT,
    age INTEGER NOT NULL,
    breed VARCHAR(255),
    gender VARCHAR(255),
    PRIMARY KEY (id)
);