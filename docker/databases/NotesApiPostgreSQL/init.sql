DROP TABLE IF EXISTS jn_comments;
DROP TABLE IF EXISTS jn_notes;
DROP TABLE IF EXISTS jn_users;
DROP TABLE IF EXISTS jn_check_list_item;
DROP TABLE IF EXISTS jn_check_list;


CREATE TABLE IF NOT EXISTS jn_users(
    user_id serial,
    user_name varchar(20) NOT NULL,
    date_of_creation timestamp NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS jn_check_list(
    check_list_id serial,
    check_list_title varchar(255) NOT NULL,
    PRIMARY KEY (check_list_id)
);

CREATE TABLE IF NOT EXISTS jn_check_list_item(
    item_id serial,
    item_name varchar(255) NOT NULL,
    item_status varchar(10) NOT NULL DEFAULT 'WAITING',
    check_list_id integer NOT NULL,
    PRIMARY KEY (item_id),
    CONSTRAINT fk_jn_check_list_item_check_list_id FOREIGN KEY (check_list_id) REFERENCES jn_check_list (check_list_id)
);


CREATE TABLE IF NOT EXISTS jn_notes(
    note_id serial,
    note_text text NOT NULL,
    date_of_creation timestamp NOT NULL,
    visibility_modifier varchar(10) NOT NULL,
    note_tag varchar(10) NOT NULL,
    owner_id integer NOT NULL,
    check_list_id integer,
    PRIMARY KEY (note_id),
    CONSTRAINT fk_jn_notes_owner_id FOREIGN KEY (owner_id) REFERENCES jn_users (user_id),
    CONSTRAINT fk_jn_notes_check_list_id FOREIGN KEY (check_list_id) REFERENCES jn_check_list (check_list_id)
);

CREATE TABLE IF NOT EXISTS jn_comments(
    comment_id serial,
    C_TYPE varchar(2),
    comment_text varchar NOT NULL,
    date_of_creation timestamp NOT NULL,
    owner_id integer NOT NULL,
    to_comment_id integer,
    to_note_id integer,
    PRIMARY KEY (comment_id),
    CONSTRAINT fk_jn_comments_owner_id FOREIGN KEY (owner_id) REFERENCES jn_users (user_id),
    CONSTRAINT fk_jn_comments_comment_id FOREIGN KEY (to_comment_id) REFERENCES jn_comments (comment_id),
    CONSTRAINT fk_jn_notes_note_id FOREIGN KEY (to_note_id) REFERENCES jn_notes (note_id)
);

INSERT INTO jn_users(user_name, date_of_creation)
VALUES  ('Max', '2023-01-17 16:00:00'),
        ('Nikita', '2023-01-17 16:00:00'),
        ('Anna', '2023-01-17 16:00:00');

INSERT INTO jn_check_list(check_list_title)
VALUES  ('Цели'),
        ('Надо');

INSERT INTO jn_check_list_item(item_name, check_list_id)
VALUES  ('Работа', 1),
        ('Учеба', 1),
        ('Дом', 1),
        ('Bread', 2),
        ('Bear', 2);

INSERT INTO jn_notes(note_text, date_of_creation, visibility_modifier, note_tag, owner_id, check_list_id)
VALUES  ('Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'NORMAL', 1, 1),
        ('Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'IMPORTANT', 1, null),
        ('Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'ARCHIVE', 1, null),
        ('SECOND Text NOTE', '2023-01-17 17:00:00', 'EVERYONE', 'NORMAL', 1, null),
        ('THRID Text NOTE', '2023-01-17 18:00:00', 'EVERYONE', 'NORMAL', 2, 2);

INSERT INTO jn_comments(C_TYPE, comment_text, date_of_creation, owner_id, to_comment_id, to_note_id)
VALUES  ('CN', 'first comment text', '2023-01-17 16:00:00', 1, null, 2),
        ('CN', 'second comment text', '2023-01-17 17:00:00', 2, null, 2),
        ('CC', 'first first comment text', '2023-01-17 17:00:00', 3, 2, null),
		('CC', 'first first first comment text', '2023-01-17 17:00:00', 1, 3, null);
