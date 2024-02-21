INSERT INTO jn_users(user_id, user_name, date_of_creation)
VALUES  (1, 'Max', '2023-01-17 16:00:00'),
        (2, 'Nikita', '2023-01-17 16:00:00'),
        (3, 'Anna', '2023-01-17 16:00:00');

INSERT INTO jn_notes(note_id, note_text, date_of_creation, visibility_modifier, note_tag, owner_id)
VALUES  (1, 'Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'NORMAL', 1),
        (2, 'SECOND Text NOTE', '2023-01-17 17:00:00', 'EVERYONE', 'NORMAL', 1),
        (3, 'THRID Text NOTE', '2023-01-17 18:00:00', 'EVERYONE', 'NORMAL', 2);

INSERT INTO jn_comments(comment_id, C_TYPE, comment_text, date_of_creation, to_comment_id, to_note_id)
VALUES  (1, 'CN', 'first comment text', '2023-01-17 16:00:00', null, 2),
        (2, 'CN', 'second comment text', '2023-01-17 17:00:00', null, 2),
        (3, 'CC', 'first first comment text', '2023-01-17 17:00:00', 2, null),
		(4, 'CC', 'first first first comment text', '2023-01-17 17:00:00', 3, null);