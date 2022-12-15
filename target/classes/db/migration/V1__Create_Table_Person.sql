CREATE TABLE IF NOT EXISTS person
(
    id serial NOT NULL,
    first_name character varying(80) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(80) COLLATE pg_catalog."default" NOT NULL,
    address character varying(100) COLLATE pg_catalog."default" NOT NULL,
    gender character varying(80) COLLATE pg_catalog."default" NOT NULL
);