DROP TABLE IF EXISTS menus_dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    roles            VARCHAR                           NOT NULL,
    registered       TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id          INTEGER NOT NULL,
    role             VARCHAR,
    CONSTRAINT user_role_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR      NOT NULL
);

CREATE TABLE menus
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR      NOT NULL,
    restaurant_id    INTEGER      NOT NULL,
    date             DATE NOT NULL DEFAULT now(),
    CONSTRAINT restaurant_menu_at_one_date_idx UNIQUE (restaurant_id, date),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE

);

CREATE TABLE dishes
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR      NOT NULL,
    price            FLOAT        NOT NULL
--     menu_id          TEXT      NOT NULL,
--     FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id          INTEGER      NOT NULL,
    menu_id          INTEGER      NOT NULL,
    date_time        TIMESTAMP(0) DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_time_idx ON votes (user_id,date_time);

CREATE TABLE menus_dishes
(
    menu_id          INTEGER      NOT NULL,
    dish_id          INTEGER      NOT NULL
);