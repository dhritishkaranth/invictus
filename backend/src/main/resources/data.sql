INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Steve','Jobs', 'stevej', 57, 'Male', '{en, es}', 'SFO', 'Brain Cancer', 'Patient', false, '{"http://facebook.com/sjobs"}', '$2a$10$YVOmS4AGg9glX0y6QFDZMuoOfiqPeOfRMymzmIjR7Vzpd4LAzJHvm', 'USER');
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Yuvraj','Singh', 'ysingh', 37, 'Male', '{hi}', 'Chandigarh', 'Lung Cancer', 'Patient', false, '{"http://facebook.com/ysingh"}', '$2a$10$oKrIuSMzqXFubhcBHTjGRuWtlzXBOF748UTGXPmN7UqZ.BwU3yV1i', 'USER');
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('Sonali','Bendre', 'sbendre', 40, 'Female', '{hi,ma}', 'Mumbai', 'Metastatic Cancer', 'Patient', true, '{"http://facebook.com/sbendre"}', '$2a$10$XTXUefno5st3Z8J7QxxuzuUg.9Rry.6SvHmBe0kVVQdrG7SxtfVhm', 'USER');
INSERT INTO users( firstname, secondname, username, age, gender, languages, location, typeOfIllness, typeOfSeeker, anonymous, resources, password, role)
VALUES ('admin','admin', 'admin', 40, 'Male', '{hi,ma}', 'Mumbai', 'admin', 'Caregiver', true, '{"http://facebook.com/admin"}', '$2a$10$QgU2lTZyBwMvvZyqeosiSu2s6j5geofdkDi33x5LyR5GJZnhuvHG.', 'ADMIN');

INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'MumbaiSupport','{ma, hi}', 'Mumbai', 'Lung Cancer', '{"http://facebook.com/mumbaigroup"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Metastatic Cancer Mumbai Support','{ma, hi}', 'Mumbai', 'Metastatic Cancer', '{"http://facebook.com/mumbaigroupMetastatic"}');
INSERT INTO groups( groupname, languages, location, typeOfIllness, resources)
VALUES ( 'Brain Cancer Mumbai Support','{en, es}', 'SFO', 'Lung Cancer', '{"http://facebook.com/sfgroup"}');

