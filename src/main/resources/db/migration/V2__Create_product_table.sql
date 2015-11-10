--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

CREATE TABLE product (
    product_id bigint NOT NULL,
    image_path character varying(255) NOT NULL,
    long_desc character varying(255),
    name character varying(255) NOT NULL,
    quantity bigint NOT NULL,
    short_desc character varying(255) NOT NULL,
    sku character varying(255) NOT NULL,
    category_id bigint NOT NULL,
    pricing_product_id bigint,
    shop_id bigint NOT NULL
);

CREATE SEQUENCE product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

