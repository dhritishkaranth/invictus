DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;

DROP TYPE IF EXISTS seeker;
DROP TYPE IF EXISTS gender;


CREATE TYPE seeker AS ENUM ('Patient', 'Caregiver');
CREATE TYPE gender AS ENUM ('Male', 'Female');

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
        resources text[]);

CREATE TABLE groups(id serial PRIMARY KEY,
        groupname VARCHAR(255) UNIQUE,
        languages text[],
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        resources text[]);