--liquibase formatted sql
--changeset Sayed:20231014_template_entity
CREATE SEQUENCE public.template_entity_sequence
    INCREMENT BY 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1000
;

ALTER SEQUENCE public.template_entity_sequence
    OWNER TO ${user_owner};

CREATE TABLE public.template_entity
(
    id                BIGINT       NOT NULL DEFAULT nextval('template_entity_sequence'::regclass),
    modification_date DATE         NOT NULL,
    creation_date     DATE         NOT NULL,
    created_by        varchar(100) NOT NULL,
    modified_by       varchar(100) NOT NULL,
    marked_as_deleted BOOLEAN               DEFAULT FALSE,
    CONSTRAINT id_pk PRIMARY KEY (id)
) TABLESPACE pg_default;

ALTER TABLE public.template_entity
    OWNER TO ${user_owner};