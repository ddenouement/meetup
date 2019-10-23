INSERT  into  roles (name) values ('ADMIN'),('SPEAKER'),('LISTENER');

INSERT into languages (name) values ('English'), ('French'), ('German'), ('Ukrainian'), ('Russian'),
                                    ('Chinese'), ('Japanese');

INSERT into meetup_states (name) values ('SCHEDULED'), ('BOOKED'), ('CANCELED'), ('IN PROGRESS'),
                                        ('TERMINATED'), ('PASSED');

INSERT into topics (name) values ('Business'), ('Technology'), ('Health'), ('Sports'), ('Politics'),
                                 ('Art');

INSERT into badges (name, script) values
    ('Polyglot', 'SELECT count(*) > 1 FROM users_languages WHERE id_user = $1;'),
    ('Star', 'SELECT count(*) >= 5 FROM subscriptions WHERE id_speaker = $1;');