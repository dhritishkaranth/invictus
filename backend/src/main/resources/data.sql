INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role, enabled)
VALUES ('Steve','Jobs', 'stevej', 57, 'Male', '{en, es}', 'SFO', 'Brain Cancer', 'Patient', false, '{"http://facebook.com/sjobs"}', '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu', 'USER', 1);
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role, enabled)
VALUES ('Yuvraj','Singh', 'ysingh', 37, 'Male', '{hi}', 'Chandigarh', 'Lung Cancer', 'Patient', false, '{"http://facebook.com/ysingh"}', '$2a$10$M2nWMmWiyfKV4ww0t7qBwOToUHDuNodLeHNGeDkF01N4HICwAyhre', 'USER', 1);
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role, enabled)
VALUES ('Sonali','Bendre', 'sbendre', 40, 'Female', '{hi,ma}', 'Mumbai', 'Metastatic Cancer', 'Patient', true, '{"http://facebook.com/sbendre"}', 'actor', 'USER', 1);

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{ma, hi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{ma, hi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{en, es}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

