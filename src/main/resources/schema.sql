CREATE TABLE IF NOT EXISTS Matches (
    id INT NOT NULL,
    player1 INT NOT NULL,
    player2 INT NOT NULL,
    match_date timestamp NOT NULL,
    PRIMARY KEY (id)
);