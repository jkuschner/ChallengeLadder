CREATE TABLE IF NOT EXISTS Players (
    id INT NOT NULL,
    name varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Matches (
    id INT NOT NULL,
    player1 INT NOT NULL,
    player2 INT NOT NULL,
    match_date timestamp NOT NULL,
    score varchar(100) NOT NULL,
    winner INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (player1) REFERENCES Players(id),
    FOREIGN KEY (player2) REFERENCES Players(id)
);
