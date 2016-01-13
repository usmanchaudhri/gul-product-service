--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table CATEGORY (
    category_id bigint not null,
    code varchar(255),
    name varchar(255) not null,
    parentCategory_category_id bigint,
    image_path varchar(255),
    created_on timestamp,
    updated_on timestamp,
    primary key (category_id)
);

CREATE SEQUENCE category_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;