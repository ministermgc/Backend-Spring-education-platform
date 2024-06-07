-- password adminadmin
INSERT INTO user_accounts
VALUES (1, 'Дмитрий', 'Носков', 'admin', 'admin@gmail.com',
        '$2y$10$m55MliMuNKnqgvywiB0J6eyF/AHjxzI8AwLKnBauRQACUC8mi/Zw.');

INSERT INTO user_accounts_roles
VALUES (2, 1);