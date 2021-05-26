INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Steve','Jobs', 'stevej', 57, 'Male', '{en, es}', 'SFO', 'Brain Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Yuvraj','Singh', 'ysingh', 37, 'Male', '{hi}', 'Chandigarh', 'Lung Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Sonali','Bendre', 'sbendre', 40, 'Female', '{hi,ma}', 'Mumbai', 'Metastatic Cancer', 'Patient', true);

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{ma, hi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{ma, hi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{en, es}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

