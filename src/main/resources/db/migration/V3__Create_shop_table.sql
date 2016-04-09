--
-- Name: shop; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE shop (
    shop_id bigint not null,
    shop_name varchar(255) not null,
    details varchar(255),
    image_path varchar(255),
    created_on timestamp,
    updated_on timestamp,
    primary key (shop_id)
);

CREATE SEQUENCE shop_shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE designer (
    designer_id bigint not null,
    image_path varchar(255),
    designer_name varchar(255) not null,
    shop_id bigint not null,
    details varchar(255),    
    created_on timestamp,
    updated_on timestamp,
    primary key (designer_id)
);

CREATE SEQUENCE designer_designer_id_seq 
	START WITH 9999 
	INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
