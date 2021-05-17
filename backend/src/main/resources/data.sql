INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Steve','Jobs', 'English', 'SFO', 'Brain Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Yuvraj','Singh', 'Punjabi', 'Chandigarh', 'Lung Cancer', 'Patient', false);
INSERT INTO users( firstname, secondname, languages, location, typeOfIllness, typeOfSeeker, anonymous)
VALUES ('Sonali','Bendre', 'Hindi', 'Mumbai', 'Metastatic Cancer', 'Patient', true);

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Lung Cancer Mumbai Support','{"Hindi","Marathi"}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{"Hindi","Marathi"}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{"English", "Spanish"}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

