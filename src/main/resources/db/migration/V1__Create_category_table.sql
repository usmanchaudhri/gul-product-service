--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table CATEGORY (
    category_id bigint not null,
    code varchar(255),
    created_on timestamp,
    name varchar(255) not null,
    updated_on timestamp,
    parentCategory_category_id bigint,
    image_path varchar(255),
    primary key (category_id)
);

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;