DROP TABLE IF EXISTS jn_comments;
DROP TABLE IF EXISTS jn_notes;

CREATE TABLE IF NOT EXISTS jn_notes(
    note_id serial,
    note_text text NOT NULL,
    date_of_creation timestamp NOT NULL,
    visibility_modifier varchar(10) NOT NULL,
    note_tag varchar(10) NOT NULL,
    PRIMARY KEY (note_id)
);

CREATE TABLE IF NOT EXISTS jn_comments(
    comment_id serial,
    C_TYPE varchar(2),
    comment_text varchar NOT NULL,
    date_of_creation timestamp NOT NULL,
    to_comment_id integer,
    to_note_id integer,
    PRIMARY KEY (comment_id),
    CONSTRAINT fk_jn_comments_comment_id FOREIGN KEY (to_comment_id) REFERENCES jn_comments (comment_id),
    CONSTRAINT fk_jn_notes_note_id FOREIGN KEY (to_note_id) REFERENCES jn_notes (note_id)
);
