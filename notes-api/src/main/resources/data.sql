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