DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;

DROP TYPE IF EXISTS seeker;

CREATE TYPE seeker AS ENUM ('Patient', 'Caregiver');
CREATE TABLE users(id serial PRIMARY KEY,
        firstname VARCHAR(255),
        secondname VARCHAR(255),
        languages text[],
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        typeOfSeeker VARCHAR(255),
        anonymous BOOLEAN);

CREATE TABLE groups(id serial PRIMARY KEY,
        groupname VARCHAR(255),
        languages text[],
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        resources text[]);