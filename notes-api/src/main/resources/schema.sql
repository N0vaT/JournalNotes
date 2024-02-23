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
