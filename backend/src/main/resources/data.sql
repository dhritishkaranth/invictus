INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Steve','Jobs', 'stevej', 57, 'Male', '{en, es}', 'SFO', 'Brain Cancer', 'Patient', false, '{"http://facebook.com/sjobs"}', '$2a$10$rN9zMzXcL5FR/5pFY3Vv6uaJ0ZQNx6XHB9EDu4VseSvmuK.yigg96', 'USER');
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Yuvraj','Singh', 'ysingh', 37, 'Male', '{hi}', 'Chandigarh', 'Lung Cancer', 'Patient', false, '{"http://facebook.com/ysingh"}', '$2a$10$M2nWMmWiyfKV4ww0t7qBwOToUHDuNodLeHNGeDkF01N4HICwAyhre', 'USER');
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Sonali','Bendre', 'sbendre', 40, 'Female', '{hi,ma}', 'Mumbai', 'Metastatic Cancer', 'Patient', true, '{"http://facebook.com/sbendre"}', '$2a$10$bNVa13IELL27UXHFp4RafeD8k9EBBwgbmjgdI3ILRVb7Z.FGyopQS', 'USER');

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{ma, hi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{ma, hi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{en, es}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

