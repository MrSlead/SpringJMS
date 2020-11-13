CREATE TABLE IF NOT EXISTS message (
    id INTEGER NOT NULL AUTO_INCREMENT,
    text VARCHAR(128) NOT NULL,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS headers (
    id INTEGER NOT NULL AUTO_INCREMENT,
    head VARCHAR(256) NOT NULL,
    FOREIGN KEY (id) references message(id),
    primary key (id)
);