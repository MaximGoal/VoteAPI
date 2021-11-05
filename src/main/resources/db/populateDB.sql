DELETE FROM menus_dishes;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users VALUES
--                          (100001, 'Admin', 'admin@mail.com', 'password', now(), true),
--                          (100002, 'User01', 'user01@mail.com', 'password', now(), true),
--                          (100003, 'User02', 'user02@mail.com', 'password', now(), true);
                         (100001, 'Admin', 'admin@mail.com', 'password', 'ADMIN'),
                         (100002, 'User01', 'user01@mail.com', 'password', 'USER'),
                         (100003, 'User02', 'user02@mail.com', 'password', 'USER');
INSERT INTO user_roles VALUES
                         (100001, 'ADMIN'),
                         (100002, 'USER'),
                         (100003, 'USER');

INSERT INTO restaurants VALUES
                         (100010, 'Restaurant #1'),
                         (100011, 'Restaurant #2'),
                         (100012, 'Restaurant #3'),
                         (100013, 'Restaurant #4');

INSERT INTO menus VALUES
                         (100020, 'Breakfast #1', 100010, now()::timestamp(0)),
                         (100021, 'Breakfast #2', 100011, now()::timestamp(0)),
                         (100022, 'Breakfast #3', 100012, now()::timestamp(0)),
                         (100023, 'Breakfast #4', 100013, now()::timestamp(0)),
                         (100024, 'Dinner #1', 100010, now()::timestamp(0) + interval '1 days'),
                         (100025, 'Dinner #2', 100011, now()::timestamp(0) + interval '1 days'),
                         (100026, 'Dinner #3', 100012, now()::timestamp(0) + interval '1 days'),
                         (100027, 'Dinner #4', 100013, now()::timestamp(0) + interval '1 days'),
                         (100028, '5 oClock #1', 100010, now()::timestamp(0) + interval '2 days'),
                         (100029, '5 oClock #2', 100011, now()::timestamp(0) + interval '2 days'),
                         (100030, '5 oClock #3', 100012, now()::timestamp(0) + interval '2 days'),
                         (100031, '5 oClock #4', 100013, now()::timestamp(0) + interval '2 days'),
                         (100032, 'Supper #1', 100010, now()::timestamp(0) + interval '3 days'),
                         (100033, 'Supper #2', 100011, now()::timestamp(0) + interval '3 days'),
                         (100034, 'Supper #3', 100012, now()::timestamp(0) + interval '3 days'),
                         (100035, 'Supper #4', 100013, now()::timestamp(0) + interval '3 days');

INSERT INTO dishes VALUES
                        (110000, 'Tea', 11.0),
                        (110001, 'Juice', 15.0),
                        (110002, 'Water', 5.0),
                        (110003, 'Bread', 2.5),
                        (110004, 'Gamburger', 20.0),
                        (110005, 'Rice', 10.0),
                        (110006, 'Spagetti', 12.0),
                        (110007, 'Rizotto', 35.5),
                        (110008, 'Jam', 1.0),
                        (110009, 'Meal', 30.0),
                        (110010, 'Chicken meal', 22.0),
                        (110011, 'Borstch', 17.0),
                        (110012, 'Schi', 16.0),
                        (110013, 'Solyanka', 16.0),
                        (110014, 'Harcho', 15.0),
                        (110015, 'Beafshteks', 33.0),
                        (110016, 'Hot dog', 23.0),
                        (110017, 'Pie', 17.0),
                        (110018, 'Tiramissu', 22.0);

INSERT INTO votes VALUES
                        (120000, 100001, 100020, now()),
                        (120001, 100002, 100020, now() + interval '1 seconds'),
                        (120002, 100003, 100021, now() + interval '2 seconds'),
                        (120003, 100001, 100025, now() + interval '3 seconds'),
                        (120004, 100002, 100024, now() + interval '4 seconds'),
                        (120005, 100003, 100025, now() + interval '15 seconds'),
                        (120006, 100001, 100031, now() + interval '16 seconds'),
                        (120007, 100002, 100030, now() + interval '17 seconds'),
                        (120008, 100003, 100030, now() + interval '18 seconds'),
                        (120009, 100001, 100035, now() + interval '170 seconds'),
                        (120010, 100002, 100035, now() + interval '171 seconds'),
                        (120011, 100003, 100035, now() + interval '172 seconds');

INSERT INTO menus_dishes VALUES
                        (100020, 110000),
                        (100020, 110015),
                        (100021, 110001),
                        (100021, 110011),
                        (100022, 110002),
                        (100022, 110012),
                        (100023, 110003),
                        (100023, 110013),
                        (100024, 110004),
                        (100025, 110005),
                        (100026, 110006),
                        (100027, 110007),
                        (100028, 110008),
                        (100029, 110009),
                        (100030, 110010),
                        (100031, 110011),
                        (100032, 110012),
                        (100033, 110013),
                        (100034, 110014),
                        (100035, 110015);