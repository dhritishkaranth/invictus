DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP SEQUENCE IF EXISTS hibernate_sequence;

DROP TYPE IF EXISTS seeker;
DROP TYPE IF EXISTS gender;


CREATE TYPE seeker AS ENUM ('Patient', 'Caregiver');
CREATE TYPE gender AS ENUM ('Male', 'Female');

CREATE SEQUENCE hibernate_sequence START 1000;

CREATE TABLE users(id serial PRIMARY KEY,
        firstname VARCHAR(255),
        secondname VARCHAR(255),
        username VARCHAR(255) UNIQUE,
        age int,
        gender VARCHAR(255),
        languages text[],
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        typeOfSeeker VARCHAR(255),
        anonymous BOOLEAN,
        resources text[],
        password VARCHAR(255),
        role VARCHAR(255) DEFAULT 'USER',
        lat DOUBLE PRECISION DEFAULT 0.0,
        lng DOUBLE PRECISION DEFAULT 0.0);

CREATE TABLE groups(id serial PRIMARY KEY,
        groupname VARCHAR(255) UNIQUE,
        languages text[],
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        resources text[],
        lat DOUBLE PRECISION DEFAULT 0.0,
        lng DOUBLE PRECISION DEFAULT 0.0);

INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('admin','admin', 'admin', 40, 'Male', '{English}', 'Mumbai', 'admin', 'Caregiver', true, '{"http://facebook.com/admin"}', '$2a$10$QgU2lTZyBwMvvZyqeosiSu2s6j5geofdkDi33x5LyR5GJZnhuvHG.', 'ADMIN');