CREATE TABLE IF NOT EXISTS books
(
    id bigint NOT NULL,
    author varchar,
    launch_date timestamp NOT NULL,
    price decimal(65,2) NOT NULL,
    title varchar,
    CONSTRAINT book_pkey PRIMARY KEY (id)
    );
