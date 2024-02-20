INSERT INTO jn_notes
VALUES  (1, 'Text NOTE', '2023-01-17 16:00:00', 'EVERYONE', 'NORMAL'),
        (2, 'SECOND Text NOTE', '2023-01-17 17:00:00', 'EVERYONE', 'NORMAL'),
        (3, 'THRID Text NOTE', '2023-01-17 18:00:00', 'EVERYONE', 'NORMAL');

INSERT INTO jn_comments
VALUES  (1, 'CN', 'first comment text', '2023-01-17 16:00:00', null, 2),
        (2, 'CN', 'second comment text', '2023-01-17 17:00:00', null, 2),
        (3, 'CC', 'first first comment text', '2023-01-17 17:00:00', 2, null),
		(4, 'CC', 'first first first comment text', '2023-01-17 17:00:00', 3, null);