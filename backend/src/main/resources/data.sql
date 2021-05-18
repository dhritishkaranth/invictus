INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Steve','Jobs', '{English, Spanish}', 'SFO', 'Brain Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Yuvraj','Singh', '{Punjabi}', 'Chandigarh', 'Lung Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Sonali','Bendre', '{Hindi,Marathi}', 'Mumbai', 'Metastatic Cancer', 'Patient', true);

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{Marathi, Hindi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{Marathi,Hindi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{English, Spanish}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

