DROP TABLE IF EXISTS users;
DROP TYPE IF EXISTS seeker;
DROP TYPE IF EXISTS yesno;

CREATE TYPE seeker AS ENUM ('Patient', 'Caregiver');
CREATE TYPE yesno AS ENUM ('Yes', 'No');
CREATE TABLE users(id serial PRIMARY KEY,
        firstname VARCHAR(255),
        secondname VARCHAR(255),
        languages VARCHAR(255),
        location VARCHAR(255),
        typeOfIllness VARCHAR(255),
        typeOfSeeker seeker,
        anonymous yesno);