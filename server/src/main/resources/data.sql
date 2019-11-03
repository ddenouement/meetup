INSERT  into  roles (name) values ('ADMIN'),('SPEAKER'),('LISTENER');

INSERT into languages (name) values ('English'), ('French'), ('German'), ('Ukrainian'), ('Russian'),
                                    ('Chinese'), ('Japanese');

INSERT into meetup_states (id, name) values (2, 'SCHEDULED'), (3, 'BOOKED'), (4, 'CANCELED'),
                                        (5, 'IN PROGRESS'), (6, 'TERMINATED'), (7, 'PASSED');

INSERT into topics (name) values ('Business'), ('Technology'), ('Health'), ('Sports'), ('Politics'),
                                 ('Art');

INSERT into badges (name, script) values
    ('Polyglot', 'SELECT count(*) > 1 FROM users_languages WHERE id_user = $1;'),
    ('Star', 'SELECT count(*) >= 5 FROM subscriptions WHERE id_speaker = $1;');

INSERT into notification_types (name) values
('MEETUP_BOOKED'), ('HOSTED_MEETUP_STARTS_SOON'), ('JOINED_MEETUP_STARTS_SOON'), ('MEETUP_INFO_CHANGED'),
('NEW_SUBSCRIBED_MEETUP'), ('LEAVE_FEEDBACK'), ('PROFILE_DEACTIVATED'), ('PROFILE_ACTIVATED');