INSERT INTO users( firstname, secondname, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Steve','Jobs', 57, 'Male', '{English, Spanish}', 'SFO', 'Brain Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Yuvraj','Singh', 37, 'Male', '{Punjabi}', 'Chandigarh', 'Lung Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Sonali','Bendre', 40, 'Female', '{Hindi,Marathi}', 'Mumbai', 'Metastatic Cancer', 'Patient', true);

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{Marathi, Hindi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{Marathi,Hindi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{English, Spanish}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

