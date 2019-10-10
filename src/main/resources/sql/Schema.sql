CREATE SCHEMA IF NOT EXISTS vegadatabase;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS languages CASCADE;
DROP TABLE IF EXISTS users_languages CASCADE;
DROP TABLE IF EXISTS meetup_states CASCADE;
DROP TABLE IF EXISTS meetups CASCADE;
DROP TABLE IF EXISTS topics CASCADE;
DROP TABLE IF EXISTS meetups_topics CASCADE;

CREATE TABLE users
(
    id         BIGSERIAL   NOT NULL,
    login      VARCHAR(30) NOT NULL UNIQUE,
    email      VARCHAR(30) NOT NULL UNIQUE,
    password   VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NULL,
    last_name  VARCHAR(30) NULL,
    active     BOOLEAN     NULL,
    about      VARCHAR(30) NULL,
    rate       REAL        NULL
        CONSTRAINT rate_range CHECK (rate >= 0 AND rate <= 5),
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   SERIAL      NOT NULL,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    id_user BIGINT  NOT NULL,
    id_role INTEGER NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_role) REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (id_user, id_role)
);

CREATE TABLE languages
(
    id   SERIAL      NOT NULL,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE users_languages
(
    id_user     BIGINT  NOT NULL,
    id_language INTEGER NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_language) REFERENCES languages (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (id_user, id_language)
);

CREATE TABLE meetup_states
(
    id   SERIAL      NOT NULL,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE meetups
(
    id           BIGSERIAL   NOT NULL,
    id_speaker   BIGINT      NOT NULL,
    id_language  INTEGER     NOT NULL,
    id_state     INTEGER     NOT NULL,
    title        VARCHAR(30) NOT NULL,
    start_time   TIMESTAMP   NOT NULL,
    min_atendees INTEGER     NOT NULL DEFAULT 1,
    max_atendees INTEGER     NOT NULL,
    description  VARCHAR(30) NULL,
    FOREIGN KEY (id_speaker) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_language) REFERENCES languages (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_state) REFERENCES meetup_states (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (id)
);

CREATE TABLE topics
(
    id   BIGSERIAL   NOT NULL,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE meetups_topics
(
    id_meetup BIGINT NOT NULL,
    id_topic  BIGINT NOT NULL,
    FOREIGN KEY (id_meetup) REFERENCES meetups (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_topic) REFERENCES topics (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (id_meetup, id_topic)
);