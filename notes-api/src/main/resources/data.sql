INSERT INTO jn_users(user_name, date_of_creation)
VALUES  ('Max', '2023-01-17 16:00:00'),
        ('Nikita', '2023-01-17 16:00:00'),
        ('Anna', '2023-01-17 16:00:00');

INSERT INTO jn_notes(note_text, date_of_creation, visibility_modifier, note_tag, owner_id)
VALUES  ('Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'NORMAL', 1),
        ('SECOND Text NOTE', '2023-01-17 17:00:00', 'EVERYONE', 'NORMAL', 1),
        ('THRID Text NOTE', '2023-01-17 18:00:00', 'EVERYONE', 'NORMAL', 2);

INSERT INTO jn_comments(C_TYPE, comment_text, date_of_creation, to_comment_id, to_note_id)
VALUES  ('CN', 'first comment text', '2023-01-17 16:00:00', null, 2),
        ('CN', 'second comment text', '2023-01-17 17:00:00', null, 2),
        ('CC', 'first first comment text', '2023-01-17 17:00:00', 2, null),
		('CC', 'first first first comment text', '2023-01-17 17:00:00', 3, null);