CREATE TABLE Users (
 user_id UUID PRIMARY KEY,
 name VARCHAR NOT NULL,
 email VARCHAR NOT NULL,
 handle VARCHAR NOT NULL,
 password VARCHAR NOT NULL,
 phone VARCHAR NOT NULL,
 accent INTEGER DEFAULT 0,
 complete UUID,
 preview UUID,
 time TIMESTAMP DEFAULT NOW()
);

CREATE UNIQUE INDEX email_index ON Users (email);
CREATE UNIQUE INDEX handle_index ON Users (handle);

CREATE TABLE Prekeys (
 client_id VARCHAR NOT NULL,
 key_id INTEGER NOT NULL,
 key VARCHAR NOT NULL,
 used BOOLEAN default FALSE
);
CREATE UNIQUE INDEX Prekeys_index ON Prekeys (client_id, key_id );

CREATE TABLE Participants (
 conv_Id UUID NOT NULL,
 user_Id UUID NOT NULL
);

CREATE UNIQUE INDEX Participants_index ON Participants (conv_Id, user_Id );

CREATE TABLE Conversations (
 conv_Id UUID PRIMARY KEY,
 creator UUID NOT NULL,
 name VARCHAR,
 type INTEGER DEFAULT 0,
 time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE Clients (
 client_Id VARCHAR PRIMARY KEY,
 user_id UUID NOT NULL,
 lastkey INTEGER NOT NULL,
 last UUID,
 time TIMESTAMP DEFAULT NOW()
);

CREATE TABLE Notifications (
 id UUID PRIMARY KEY,
 client_Id VARCHAR NOT NULL,
 user_id UUID NOT NULL,
 notification VARCHAR NOT NULL,
 time TIMESTAMP DEFAULT NOW()
);

CREATE INDEX Notifications_index ON Notifications (client_Id, user_Id, time);

